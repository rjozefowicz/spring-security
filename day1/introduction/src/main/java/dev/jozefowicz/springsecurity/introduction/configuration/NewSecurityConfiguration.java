package dev.jozefowicz.springsecurity.introduction.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Configuration
public class NewSecurityConfiguration {

    private final ObjectMapper objectMapper;

    public NewSecurityConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers(HttpMethod.GET,"/contact").permitAll()
                            .anyRequest().authenticated();
                })
                .httpBasic(httpBasic -> {
                    httpBasic.authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.addHeader("WWW-Authenticate", "Basic");
                        response.getWriter().println(this.objectMapper.writeValueAsString(Map.of("message", "please authenticate")));
                    });
                });
        return http.build();
    }

}
