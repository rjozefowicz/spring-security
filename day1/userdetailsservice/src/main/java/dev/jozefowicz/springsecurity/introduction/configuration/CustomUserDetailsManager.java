package dev.jozefowicz.springsecurity.introduction.configuration;

import dev.jozefowicz.springsecurity.introduction.domain.User;
import dev.jozefowicz.springsecurity.introduction.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsManager implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> persistedUser = userRepository.findByUsername(username);
        if (persistedUser.isPresent()) {
            return new PersistedUserDetails(persistedUser.get().getUsername(), persistedUser.get().getPassword());
        }
        throw new UsernameNotFoundException("");
    }

    @Override
    public void createUser(UserDetails user) {
        userRepository.save(new User(user.getUsername(), user.getPassword()));
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
