package dev.jozefowicz.springsecurity.centralizedconfig.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * TODO #1 - Przeskanuj w poszukiwaniu konfiguracji, beanów itp następujące pakiety
 *      "dev.jozefowicz.springsecurity.centralizedconfig.application",
 *      "dev.jozefowicz.springsecurity.centralizedconfig.module1"
 */
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
