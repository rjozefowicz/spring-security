package dev.jozefowicz.springsecurity.authenticationmanager.configuration.internalsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class InternalSystemAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private InternalSystemUserRepository internalSystemUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();

        if (internalSystemUser(username)) {
            final Optional<InternalSystemUserRepository.InternalUser> internalUser = internalSystemUserRepository.findByUsername(username);
            if (internalUser.isPresent()) {
                final String password = authentication.getCredentials().toString();
                if (internalUser.get().getPassword().equals(password)) {
                    return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
                }
            }
            throw new BadCredentialsException("Bad credentials");
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean internalSystemUser(String username) {
        return nonNull(username) && username.startsWith("agx");
    }

}
