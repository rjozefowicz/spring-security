package dev.jozefowicz.springsecurity.jwtadvanced.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("jan@example.com")) {
            return new User("jan@example.com", "password", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")));
        } else if (username.equals("stefan@example.com")) {
            return new User("stefan@example.com", "password1", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        }
        throw new UsernameNotFoundException("user nor found");
    }
}
