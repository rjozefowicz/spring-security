package dev.jozefowicz.springsecurity.introduction.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {
    private String token;
    private LocalDateTime expirationDate;
    private User user;

    public Token(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expirationDate = LocalDateTime.now().plusHours(12);
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

}
