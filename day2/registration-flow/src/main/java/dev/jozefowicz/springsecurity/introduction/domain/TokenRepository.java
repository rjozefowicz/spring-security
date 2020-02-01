package dev.jozefowicz.springsecurity.introduction.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TokenRepository {

    private final List<Token> tokens = new CopyOnWriteArrayList<>();

    public Optional<Token> findByToken(final String token) {
        return tokens.stream().filter(user -> user.getToken().equals(token)).findFirst();
    }

    public void persist(final Token token) {
        tokens.add(token);
    }

}
