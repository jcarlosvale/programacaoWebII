package com.study.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("professor")
        .password(passwordEncoder.encode("1234"))
        .authorities("ROLE_PROFESSOR")
        .and()
        .withUser("aluno")
        .password(passwordEncoder.encode("1234"))
        .authorities("ROLE_ALUNO");
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/h2-console/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
        .permitAll()
        .antMatchers("/professores/**")
        .hasAuthority("ROLE_PROFESSOR")
        .anyRequest()
        .authenticated()
        .antMatchers("/alunos/**")
        .hasAuthority("ROLE_ALUNO")
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
return http.build();

	}
}
