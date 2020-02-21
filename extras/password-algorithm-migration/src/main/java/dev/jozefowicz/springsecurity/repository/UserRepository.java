package dev.jozefowicz.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import dev.jozefowicz.springsecurity.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}