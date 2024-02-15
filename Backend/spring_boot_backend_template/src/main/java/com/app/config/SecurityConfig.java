package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
		                   .authorizeHttpRequests()
		                   .antMatchers("/admin/**")
		                   .hasRole("ADMIN")
		                   .antMatchers("/dashboard/**")
		                   .hasRole("CUSTOMER")
		                   .antMatchers("/feedback")
		                   .hasAuthority("CUSTOMER")
		                   .anyRequest()
		                   .permitAll()
		                   .and()
		                   .formLogin();
		
		return httpSecurity.build();
		
	}
}
