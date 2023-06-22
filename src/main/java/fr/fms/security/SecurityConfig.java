package fr.fms.security;

import java.nio.file.AccessDeniedException;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	PasswordEncoder pe = passwordEncoder();
	auth.inMemoryAuthentication().withUser("admin").password(pe.encode("12345")).roles("ADMIN","USER");
	auth.inMemoryAuthentication().withUser("user").password(pe.encode("12345")).roles("USER");
auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
}
@Bean
private PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

@Override
protected void configure(HttpSecurity http) throws Exception{
	http.formLogin();
	http.authorizeRequests().antMatchers("/ccinema","/cfilm","/csession","/ucinema","/ufilm","/usession").hasRole("ADMIN");
	http.authorizeRequests().antMatchers("/films","/sessions","/404","/403","/sessionRes","/cinemas").hasRole("USER");
	http.exceptionHandling().accessDeniedPage("/403");
}
}
