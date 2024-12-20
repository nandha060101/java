package com.neeyamo.approvalflow.apiaccess;

import static org.springframework.security.oauth2.jwt.JwtClaimNames.ISS;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.log.LogMessage;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWTParser;

@Component
public class MyJwtAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {

	private final Log logger = LogFactory.getLog(getClass());

	private final Map<String, AuthenticationManager> authenticationManagers = new ConcurrentHashMap<>();

	private final JwtClaimIssuerConverter issuerConverter = new JwtClaimIssuerConverter();
	private BearerTokenResolver resolver = new DefaultBearerTokenResolver();
	@Autowired
	JWTIssuersProps props;

	@Override
	public AuthenticationManager resolve(HttpServletRequest context) {
		String issuer = issuerConverter.convert(context);
		if (props.getIssuers().contains(issuer)) {
			AuthenticationManager authenticationManager = this.authenticationManagers.computeIfAbsent(issuer, k -> {
				this.logger.info("Constructing AuthenticationManager");
				JwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);
				JwtAuthenticationProvider provider = new JwtAuthenticationProvider(jwtDecoder);
				final CustomJwtAuthenticationConverter jwtAuthenticationConverter = new CustomJwtAuthenticationConverter(
						"account");
				provider.setJwtAuthenticationConverter(jwtAuthenticationConverter);
				return provider::authenticate;
			});
			this.logger.info(LogMessage.format("Resolved AuthenticationManager for issuer '%s'", issuer));
			return authenticationManager;
		} else {
			this.logger.info("Did not resolve AuthenticationManager since issuer is not trusted");
		}
		return null;
	}

	private class JwtClaimIssuerConverter implements Converter<HttpServletRequest, String> {
		@Override
		public String convert(@NonNull HttpServletRequest request) {
			try {
				return Optional
						.ofNullable(JWTParser.parse(resolver.resolve(request)).getJWTClaimsSet().getStringClaim(ISS))
						.orElseThrow(() -> new InvalidBearerTokenException("Missing issuer"));
			} catch (Exception ex) {
				throw new InvalidBearerTokenException(ex.getMessage(), ex);
			}
		}
	}

}