package dev.jozefowicz.springsecurity.formlogin2.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UserRepository {

    private final List<User> users = new CopyOnWriteArrayList<>();

    public void persist(final User user) {
        users.add(user);
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.username.equals(username)).findFirst();
    }

    public static class User {

        private String username;
        private String password;
        private List<String> roles;

        public User() {
        }

        public User(String username, String password, List<String> roles) {
            this.username = username;
            this.password = password;
            this.roles = Collections.unmodifiableList(roles);
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public List<String> getRoles() {
            return roles;
        }
    }
}
