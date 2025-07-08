package com.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig
{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						// Swagger UI endpoints - ALLOW PUBLIC ACCESS
						.requestMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
						.requestMatchers("/v3/api-docs/**", "/swagger-resources/**").permitAll()
						.requestMatchers("/webjars/**").permitAll()

						// Public API endpoints
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/products/**").permitAll()
						.requestMatchers("/api/categories/**").permitAll()
						.requestMatchers("/api/cart/**").permitAll()
						.requestMatchers("/h2-console/**").permitAll()

						// Admin endpoints
						.requestMatchers("/api/admin/**").permitAll()

						// All other requests require authentication
						.anyRequest().authenticated()
				);


		// For H2 Console
		http.headers(headers -> headers.frameOptions().disable());

		return http.build();
	}
}