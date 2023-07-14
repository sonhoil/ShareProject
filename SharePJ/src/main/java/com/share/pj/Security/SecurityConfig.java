package com.share.pj.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.share.pj.Security.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
            .antMatchers("/api/login").authenticated()
            .antMatchers("/api/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
      
            .logout()
                .logoutSuccessUrl("/111")
        .and() .oauth2Login()
        .userInfoEndpoint()
        .userService(customOAuth2UserService);
    }
}