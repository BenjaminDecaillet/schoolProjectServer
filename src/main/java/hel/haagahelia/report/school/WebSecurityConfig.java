package hel.haagahelia.report.school;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import hel.haagahelia.report.school.service.StudentDetailServiceImpl;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
        .authorizeRequests().antMatchers("/css/**","/students").permitAll()
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