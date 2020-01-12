package dev.jozefowicz.springsecurity.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomAccessDecisionVoterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomAccessDecisionVoterApplication.class, args);
	}

}
