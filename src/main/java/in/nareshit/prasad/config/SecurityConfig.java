package in.nareshit.prasad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	protected AuthenticationManager authenticationManager()
//	{
//		// TODO Auto-generated method stub
//		return super.authenticationManager();
//
//	}
	
	@Autowired
	private InvalidUserAuthenticationEntryPoint authenticationEntryPoint;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.
		userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.csrf().disable()
		.authorizeRequests()
		.requestMatchers("user/save","/user/login").permitAll()
		.anyRequest()
//		.and()
//	.exceptionHandling()
//		.authenticationEntryPoint(authenticationEntryPoint)//Optional
//		.and()
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//
		;
	}
	
	
	
}
