package com.erp.springboot.auth;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig
{
	@Autowired
    public MyAuthFailureHandler myAuthFailureHandler;
	
	@Autowired
	private MyAuthSuccessHandler myAuthSuccessHandler;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf)->csrf.disable()) 
			.cors((cors)-> cors.configurationSource(corsConfigurationSource())) // disable에서 변경해서 react에서 접근 허용 
			.authorizeHttpRequests((request) -> request	// http 요청에 대한 인가 설정 처리
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() 
				.requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
				.anyRequest().permitAll() // 로그인 구현시 .authenticated()로 변경할것
			);
		http.formLogin((formLogin) -> formLogin
				.loginPage("/myLogin.do")		// default : /login
				.loginProcessingUrl("/myLoginAction.do")
				.successHandler(myAuthSuccessHandler)
//				.failureUrl("/myError.do") 		// default : /login?error
				.failureHandler(myAuthFailureHandler)
				.usernameParameter("my_id") 	// default : username
				.passwordParameter("my_pass")	// default : password
				.permitAll());
		
		http.logout((logout) -> logout			// default : /logout
				.logoutUrl("/myLogout.do")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
				.permitAll());
		
		http.exceptionHandling((expHandling) -> expHandling
				.accessDeniedPage("/denied.do"));
		return http.build();
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT user_id, user_pw, enable "
					+ " FROM USER_INFO WHERE user_id = ?")
			.authoritiesByUsernameQuery("SELECT user_id, authority "
					+ " FROM USER_INFO WHERE user_id =?")
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// CORS설정으로 RESTAPI사용시 보안 문제로 막히지 않게 설정
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:5173");
		configuration.addAllowedHeader("*");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);	// 인증 허용
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
}