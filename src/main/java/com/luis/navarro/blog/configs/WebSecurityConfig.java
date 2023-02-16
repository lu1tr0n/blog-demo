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
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
    
    /*
     * https://hellokoding.com/spring-security-login-logout-thymeleaf/
     * https://www.codejava.net/frameworks/spring-boot/fix-websecurityconfigureradapter-deprecated
     * https://stackoverflow.com/questions/74609057/how-to-fix-spring-authorizerequests-is-deprecated
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http
	    	.cors()
	    	.and()
	    	.csrf()
	    		.disable()
	    	.authorizeHttpRequests()
	    		.requestMatchers("/assets/frontend/css/**", "/assets/frontend/js/**", "/registration")
	    		.permitAll()
	    	.anyRequest()
	    		.authenticated()
	    	.and()
	    	.formLogin()
	    		.loginPage("/login")
	    		.defaultSuccessUrl("/welcome")
	    		.failureUrl("/login?error")
	    		.permitAll()
	    	.and()
	    	.logout()
            	.logoutSuccessUrl("/login?logout=true")
            	.invalidateHttpSession(true)
	    		.permitAll();
	    	
	    	http.headers().frameOptions().sameOrigin();
	    	
    	return http.build();
    }
    
    /* 
     * @description: Error when login 
     * site: https://stackoverflow.com/questions/61029340/spring-security-redirects-to-page-with-status-code-999
     * */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico", "/resources/**", "/error");
    }
}