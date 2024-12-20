package com.neeyamo.approvalflow.apiaccess;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/externalapi/**").authorizeRequests().antMatchers("/externalapi/**")
				.hasRole("APIADMIN").and().httpBasic().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("approvalFlowAdmin")
				.password("$2a$10$XqV/096KB9X1f2uwur5SxeD8BUnC.2mJ0q9X8wj/G.SF1UXXC6svC").roles("APIADMIN");
	}

}