package com.chargemanag1.bankmanag1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
*/
import org.springframework.util.AntPathMatcher;

/*
@Configuration
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
    .formLogin()
    .loginPage("/login").permitAll()
    .and()
    .logout().invalidateHttpSession(true).clearAuthentication(true)
    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    .logoutSuccessUrl("/logout-success").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
}
    }
*/
//(exclude =SecurityAutoConfiguration.class)

@ComponentScan(basePackages = {"com.chargemanag1.bankmanag1.*"})
@SpringBootApplication
public class Bankmanag1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankmanag1Application.class, args);
		System.out.println(" done ");
	}

}
