package dev.jozefowicz.springsecurity.jaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.security.auth.login.LoginContext;

@SpringBootApplication
public class JaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaasApplication.class, args);
	}

}
