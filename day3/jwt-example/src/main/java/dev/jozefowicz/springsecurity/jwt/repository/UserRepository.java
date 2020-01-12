package dev.jozefowicz.springsecurity.jwt.repository;

import dev.jozefowicz.springsecurity.jwt.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class UserRepository implements Repository<User> {

    private List<User> users = new CopyOnWriteArrayList<User>();

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void persist(User user) {
        users.add(user);
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}
