package com.share.pj.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
		.csrf().disable()   //csrf 비활성화하고자 하는 경우
			//.addFilterAfter(customAuthenticationFilter(), CsrfFilter.class)
			.authorizeRequests()
			.antMatchers("/api/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
			//.antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
			//.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
			.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
			.and().headers()
			.frameOptions().sameOrigin()
			.and()
			.formLogin()
			.loginPage("/login")
			//.successHandler(customAuthenticationSuccessHandler)
			.permitAll()
			.and()
			.logout()
			.permitAll()
			.and()
			.rememberMe()
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(60 * 60* 60 * 24 * 14);
			//.userDetailsService(securityService);
    }
    
  /*  @Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter(
				new AntPathRequestMatcher("/doLogin", HttpMethod.POST.name())
		);
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
	return filter;
	}*/
}