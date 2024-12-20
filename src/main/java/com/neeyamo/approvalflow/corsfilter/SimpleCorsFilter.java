package com.neeyamo.approvalflow.corsfilter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.neeyamo.approvalflow.utility.Util;

import datadog.trace.api.CorrelationIdentifier;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
	private static final String ACCESS_CONTROL = "Access-Control-Allow-Origin";
	
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		MDC.put("dd.trace_id", CorrelationIdentifier.getTraceId());
		MDC.put("dd.span_id", CorrelationIdentifier.getSpanId());
		MDC.put("requestid", request.getHeader("requestid"));
		
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type,Authorization,X-Amz-Date,X-Api-Key,X-Amz-Security-Token,Accept");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Content-Security-Policy", "frame-src 'self'; frame-ancestors 'self'; object-src 'none';");
		Pattern hostAllowedPattern = Pattern.compile("(.+\\.)*neeyamo\\.works|com", Pattern.CASE_INSENSITIVE);
		String origin = ((HttpServletRequest) req).getHeader("Origin");
		if (Util.isNotNullOrEmpty(origin) && hostAllowedPattern.matcher(origin).matches()) {
			response.setHeader(ACCESS_CONTROL, origin);
		}

		else {
			response.setHeader(ACCESS_CONTROL, "https://neosuiteuat.neeyamo.works");
		}

		if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		// Do nothing.
	}

	@Override
	public void destroy() {
		// Do nothing.
	}
}
