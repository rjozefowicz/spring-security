package dev.jozefowicz.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class UserPasswordMigration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private LocalDateTime migrationDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getMigrationDate() {
        return migrationDate;
    }

    public void setMigrationDate(LocalDateTime migrationDate) {
        this.migrationDate = migrationDate;
    }
}
