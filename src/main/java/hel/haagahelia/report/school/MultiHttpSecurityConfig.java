package hel.haagahelia.report.school;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import hel.haagahelia.report.school.service.StudentDetailServiceImpl;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

	@Configuration
	@Order(1) 
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private StudentDetailServiceImpl studentDetailsService;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(studentDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}
		
		protected void configure(HttpSecurity http) throws Exception {
			//Testing purposes
//			http
//			.antMatcher("/api/**")
//			.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();
			

			http
			.antMatcher("/api/**")
			.csrf().disable().cors().and().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/login").permitAll()
			.anyRequest().authenticated()
			.and()
			// Filter for the api/login requests
			.addFilterBefore(new LoginFilter("/api/login",
					authenticationManager()),
					UsernamePasswordAuthenticationFilter.class)
			// Filter for other requests to check JWT in header
			.addFilterBefore(new AuthenticationFilter(),
					UsernamePasswordAuthenticationFilter.class);
		}
	}

	@Configuration                                              
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private StudentDetailServiceImpl studentDetailsService;

		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(studentDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests().antMatchers("/css/**").permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/", "addsubject", "addgrade/{id}", "subjectlist", "delete/{id}",
					"editgrade/{id}", "savegrade", "deletegrade/{id}", "editsubject/{id}", "savesubject").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/subjectlist")
			.permitAll()
			.and()
			.logout()
			.permitAll();
		}
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = 
				new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();

		source.registerCorsConfiguration("/**", config);
		return source;
	}
}