package dev.jozefowicz.springsecurity.permissionevaluator.repository;

import dev.jozefowicz.springsecurity.permissionevaluator.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements Repository<User> {

    private List<User> users = new ArrayList<>();

    @Override
    public void persist(User entity) {
        users.add(entity);
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(long id) {
        return users.stream().filter(u -> u.getId() == id).findAny();
    }

    public Optional<User> findByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findAny();
    }

}
