package org.serratec.backend.config;

import org.serratec.backend.security.AuthService;
import org.serratec.backend.security.JWTAutheticationFilter;
import org.serratec.backend.security.JWTAuthorizationFilter;
import org.serratec.backend.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthService service;
	
	@Autowired
	JWTUtil jwtUtil;
	
	private static final String[] AUTH_WHITELIST = {"/cliente/recuperarSenha" ,"/produto/listarProdutos", 
			"/cliente/criarCliente", "/categoria/listarCategorias"};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
     	.antMatchers(AUTH_WHITELIST).permitAll()
//		.antMatchers(HttpMethod.GET, "/produtos").permitAll()
		.anyRequest().authenticated();
		http.addFilterBefore(new JWTAutheticationFilter(authenticationManager(),jwtUtil), 
				UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder());
	}
}
