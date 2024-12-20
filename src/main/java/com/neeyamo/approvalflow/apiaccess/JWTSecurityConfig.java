package com.neeyamo.approvalflow.apiaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)  
public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyJwtAuthenticationManagerResolver trustedIssuerJwtAuthenticationManagerResolver;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable();
		http.authorizeHttpRequests(authorize -> authorize
				.antMatchers("/api/**")
				.authenticated())
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
						.authenticationManagerResolver(this.trustedIssuerJwtAuthenticationManagerResolver));

	}

}