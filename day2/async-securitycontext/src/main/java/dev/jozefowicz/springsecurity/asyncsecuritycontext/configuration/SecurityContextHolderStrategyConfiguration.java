package dev.jozefowicz.springsecurity.asyncsecuritycontext.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

//@Configuration
public class SecurityContextHolderStrategyConfiguration {

    @PostConstruct
    public void setup() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

}
