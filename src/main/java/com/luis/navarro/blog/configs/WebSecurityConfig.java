package com.luis.navarro.blog.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = false, securedEnabled = true)
public class WebSecurityConfig {
	
    @Qualifier("UserDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	/*
    	 * http
            .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    	 * */
    	/*http.cors().and().csrf().disable().authorizeHttpRequests(authorize -> authorize
    	.requestMatchers("/, /login, /registration, /logout").permitAll()
        .requestMatchers("/api").hasRole("ADMIN")
        .requestMatchers("/users").hasRole("USER")
        .anyRequest().authenticated())
        .logout().logoutUrl("/logout").logoutSuccessUrl("/").and()
        .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/users").failureUrl("/login?error");*/
	    	http.authorizeHttpRequests().requestMatchers("/assets/frontend/css/**", "/assets/frontend/js/**", "/registration").permitAll()
	    	.anyRequest().authenticated()
	    	.and()
	    	.formLogin().loginPage("/login").permitAll()
	    	.and()
	    	.logout().permitAll();
    	return http.build();
    }
    
}