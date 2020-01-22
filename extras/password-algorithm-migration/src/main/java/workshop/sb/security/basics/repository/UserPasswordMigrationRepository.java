package workshop.sb.security.basics.repository;

import org.springframework.data.repository.CrudRepository;
import workshop.sb.security.basics.model.UserPasswordMigration;

public interface UserPasswordMigrationRepository extends CrudRepository<UserPasswordMigration, Long> {
    boolean existsByUserId(long userId);
}