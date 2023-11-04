package dev.jozefowicz.springsecurity.apikey.configuration;

import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    private final String authority;

    public CustomGrantedAuthority(APIKeyRepository.RoleEnum role) {
        this.authority = role.name();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
