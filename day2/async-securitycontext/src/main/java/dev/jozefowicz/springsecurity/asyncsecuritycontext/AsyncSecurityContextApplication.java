package dev.jozefowicz.springsecurity.asyncsecuritycontext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AsyncSecurityContextApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncSecurityContextApplication.class, args);
	}

}
