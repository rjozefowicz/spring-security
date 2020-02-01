package dev.jozefowicz.springsecurity.application.configuration;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

public class MiddayAccessDecisionVoter implements AccessDecisionVoter {

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {

        /*
        TODO
            Zaimplementuj głosowanie tak, że użytkownik z rolą ADMIN ma zawsze ACCESS_GRANTED (1)
            a użytkownik z rolą USER tylko do południa, inaczej ma ACCESS_ABSTAIN (0)
         */

        return -1;
    }

    @Override
    public boolean supports(Class aClass) {
        return true;
    }
}
