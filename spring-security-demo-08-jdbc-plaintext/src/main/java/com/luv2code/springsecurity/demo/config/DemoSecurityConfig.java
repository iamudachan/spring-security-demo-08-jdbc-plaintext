package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;


@Configurable
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource securityDataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * auth.inMemoryAuthentication().withUser(users.username("john").password(
		 * "test123").roles("EMPLOYEE","ADMIN"));
		 * auth.inMemoryAuthentication().withUser(users.username("kiran").password(
		 * "test123").roles("EMPLOYEE","MANAGER"));
		 * auth.inMemoryAuthentication().withUser(users.username("mahesh").password(
		 * "test123").roles("EMPLOYEE"));
		 */
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
//			.anyRequest().authenticated()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin().loginPage("/showLoginPage")
			.loginProcessingUrl("/authonticateTheUser").permitAll()
			.and().logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
}
