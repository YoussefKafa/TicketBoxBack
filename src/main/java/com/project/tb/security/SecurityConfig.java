package com.project.tb.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.project.tb.services.CustomUserDetailsService;
import static com.project.tb.security.SecurityConstants.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true, // make sure that whenever we want to add very specific security , for the future
    jsr250Enabled = true,
    prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private JwtAuthenticationEntryPoint unathorizedHandler;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	public JwtAuthenticationFilter authinticationFilter() { return new JwtAuthenticationFilter();}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
    @Override
     public void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
    .exceptionHandling().authenticationEntryPoint(unathorizedHandler)
    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
   .authorizeRequests().antMatchers("/api/stadium/show/**").permitAll()
		   .antMatchers("/api/game/show/**").permitAll()
		   .antMatchers("/api/gameTeams/show/**").permitAll()
		   .antMatchers("/api/team/show/**").permitAll()
		   .antMatchers("/api/ticket/show/**").permitAll()
		   .antMatchers("/api/users/show/**").permitAll()
    .anyRequest().authenticated().and()
    .formLogin().loginPage(LOGIN_FORM).permitAll()
    ;
    }
}