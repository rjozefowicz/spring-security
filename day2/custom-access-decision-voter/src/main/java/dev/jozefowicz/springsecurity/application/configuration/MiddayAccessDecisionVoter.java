package dev.jozefowicz.springsecurity.application.configuration;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalTime;
import java.util.Collection;

public class MiddayAccessDecisionVoter implements AccessDecisionVoter {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {
        return authentication
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            // TODO użytkownik ADMIN ma zawsze dostęp, użytkownik z rolą USER tylko do południa
            .filter(authority -> "ROLE_ADMIN".equals(authority) || ("ROLE_USER".equals(authority) && LocalTime.now().isBefore(LocalTime.parse("12:01:00"))))
            .findAny()
            .map(authority -> ACCESS_GRANTED)
            .orElseGet(() -> ACCESS_ABSTAIN);
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }
}
