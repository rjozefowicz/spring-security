package dev.jozefowicz.springsecurity.ipwhitelisting.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

//@Configuration
//@EnableWebSecurity
public class MultipleIPAddressesSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${whitelist.ip.addresses}")
    private Set<String> whitelistedIpAddresses;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequest -> {
            authorizeRequest
                    .antMatchers("/public")
                    .permitAll()
                    .antMatchers("/restricted")
                    .access(buildHasIpAddressExpression(whitelistedIpAddresses))
                    .anyRequest()
                    .authenticated();
        });
    }

    private String buildHasIpAddressExpression(Collection<String> ipAddresses) {
        return ipAddresses
                .stream()
                .map(ipAddress -> String.format("hasIpAddress('%s')", ipAddress))
                .collect(Collectors.joining(" or "));
    }

}
