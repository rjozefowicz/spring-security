package dev.jozefowicz.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import dev.jozefowicz.springsecurity.model.UserPasswordMigration;

public interface UserPasswordMigrationRepository extends CrudRepository<UserPasswordMigration, Long> {
    boolean existsByUserId(long userId);
}