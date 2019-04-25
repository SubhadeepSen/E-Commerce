package sdp.shop.now.dao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ShopNowSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//adding authentication for accessing the REST APIs.
		http.csrf().disable().authorizeRequests().antMatchers("/api/**").authenticated().and().httpBasic()
				.authenticationEntryPoint(authEntryPoint);
		//disabling authentication for accessing H2 Database.
		http.headers().frameOptions().disable().and().authorizeRequests().antMatchers("/db-console/**").permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//adding username and password.
		auth.inMemoryAuthentication().withUser("sunny12345").password("{noop}12345").roles("USER");
	}

}
