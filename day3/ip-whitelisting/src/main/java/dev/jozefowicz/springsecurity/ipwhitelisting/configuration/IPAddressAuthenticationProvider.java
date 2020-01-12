package dev.jozefowicz.springsecurity.ipwhitelisting.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class IPAddressAuthenticationProvider implements AuthenticationProvider {

    @Value("${whitelist.ip.addresses}")
    private Set<String> whitelistedIpAddresses;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String userIp = details.getRemoteAddress();
        if(! whitelistedIpAddresses.contains(userIp)){
            throw new BadCredentialsException("Invalid IP Address");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), null, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
