package com.gestiondesUtilisateur.security;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.gestiondesUtilisateur.Service.UserService;
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter{
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	

	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
		http
		.cors()
		.and()
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,SecurityConstants.SIGN_UP_URL)
		.permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilter(  getAuthenticationFilter()) 
        .addFilter(new AuthorizationFilter(authenticationManager()));
		
	} 
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
	    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/users/login");
	    return filter;
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
}
