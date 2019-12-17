package gameover.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages="gameover")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	
	@Bean 
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		 return new CustomAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/gestion/categoria/**").hasAnyRole("ADMIN")
			.antMatchers("/gestion/tipo/**").hasAnyRole("ADMIN")
			.antMatchers("/gestion/usuarios/**").hasRole("ADMIN")
			.antMatchers("/gestion/articulo/eliminar/**").hasAnyRole("ADMIN")
			.antMatchers("/gestion/articulo/**").hasAnyRole("EDITOR","ADMIN")
			.antMatchers("/gestion/**").hasAnyRole("EDITOR","ADMIN")
			.antMatchers("/file/**").permitAll()
			.antMatchers("/").permitAll()
			.and().formLogin().loginPage("/userLogin").loginProcessingUrl("/authUsuario")
			.successHandler(customAuthenticationSuccessHandler())
			.failureHandler(customAuthenticationFailureHandler()).permitAll()
			.and().logout().logoutUrl("/userLogout").logoutSuccessUrl("/").permitAll()
			.and().exceptionHandling().accessDeniedPage("/prohibido");
	}
}