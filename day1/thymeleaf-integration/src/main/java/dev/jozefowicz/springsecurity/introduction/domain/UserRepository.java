package dev.jozefowicz.springsecurity.introduction.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UserRepository {

    private List<User> users = new CopyOnWriteArrayList<>();

    public UserRepository() {
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
}
