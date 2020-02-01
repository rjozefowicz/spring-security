package dev.jozefowicz.springsecurity.introduction.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UserRepository {

    private final List<User> users = new CopyOnWriteArrayList<>();

    public Optional<User> findByName(final String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    public void persist(final User user) {
        users.add(user);
    }

}
