package com.vk.audit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
  
@EnableWebSecurity
@Configuration
public class SecurityConfiguration  {
  
	@Bean
	protected InMemoryUserDetailsManager configureAuthentication() {
	
		// Defining ADMIN and NONADMIN roles
		List<GrantedAuthority> adminRoles = new ArrayList<>();
		adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
		
		List<GrantedAuthority> nonAdminRoles = new ArrayList<>();
		nonAdminRoles.add(new SimpleGrantedAuthority("NONADMIN"));
		
		// Assigning role to users
		List<UserDetails> users = new ArrayList<>();
		users.add(new User("admin", "{noop}password", adminRoles));	
		users.add(new User("vk", "{noop}password", nonAdminRoles));
		users.add(new User("nonadmin", "{noop}password", nonAdminRoles));
		users.add(new User("pk", "{noop}password", nonAdminRoles));
		return new InMemoryUserDetailsManager(users);
		
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/audit").hasAuthority("ADMIN")
		.antMatchers("/audit/*").hasAnyAuthority("NONADMIN","ADMIN")
		.antMatchers("/", "/**", "/h2/**").permitAll()             
        .and()
        .csrf().disable()
        .headers().frameOptions().disable()
		.and().formLogin();
		
		return http.build();
		
	}
	
}
