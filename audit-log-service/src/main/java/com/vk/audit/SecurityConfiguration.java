package com.vk.audit;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.audit.dto.User;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	protected InMemoryUserDetailsManager configureAuthentication() {

		// Defining ADMIN and NONADMIN roles
		List<GrantedAuthority> adminRoles = new ArrayList<>();
		adminRoles.add(new SimpleGrantedAuthority("ADMIN"));

		List<GrantedAuthority> nonAdminRoles = new ArrayList<>();
		nonAdminRoles.add(new SimpleGrantedAuthority("NONADMIN"));

		// Assigning role to users
		List<UserDetails> users = new ArrayList<>();
		this.getUserList().forEach((user) -> {
			users.add(new org.springframework.security.core.userdetails.User(user.getName(),
					"{noop}" + user.getPassword(),
					"ADMIN".equalsIgnoreCase(user.getRole()) ? adminRoles : nonAdminRoles));
		});

		return new InMemoryUserDetailsManager(users);

	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/audits").hasAnyAuthority("NONADMIN", "ADMIN")
			.antMatchers("/audits/*").hasAnyAuthority("NONADMIN", "ADMIN")
			.antMatchers("/", "/**", "/h2/**").permitAll()
			.and().csrf().disable()
			.headers().frameOptions().disable()
			.and().formLogin();

		return http.build();

	}

	private List<User> getUserList() {
		final ObjectMapper objectMapper = new ObjectMapper();
		InputStream fileStream = TypeReference.class.getResourceAsStream("/static/users.json");
		List<User> userList = new ArrayList<>();
		try {
			userList = objectMapper.readValue(fileStream, new TypeReference<List<User>>() {
			});
			return userList;
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}

}
