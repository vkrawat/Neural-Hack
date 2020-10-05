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

//(exclude =SecurityAutoConfiguration.class)

@ComponentScan(basePackages = {"com.chargemanag1.bankmanag1.*"})
@SpringBootApplication
public class Bankmanag1Application {

	public static void main(String[] args) {
		SpringApplication.run(Bankmanag1Application.class, args);
		System.out.println(" done ");
	}

}
