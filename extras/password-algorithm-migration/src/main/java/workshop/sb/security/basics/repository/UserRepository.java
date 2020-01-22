package workshop.sb.security.basics.repository;

import org.springframework.data.repository.CrudRepository;
import workshop.sb.security.basics.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}