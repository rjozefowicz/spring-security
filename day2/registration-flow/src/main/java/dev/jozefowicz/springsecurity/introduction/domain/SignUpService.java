package dev.jozefowicz.springsecurity.introduction.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SignUpService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void signUp(final String email, final String password) {
        final User user = new User(email, password);
        final Token token = new Token(user);
        System.out.println("Verification token: " + token.getToken());
        tokenRepository.persist(token);
        userRepository.persist(user);
    }

    public void verify(final String token) {
        final Optional<Token> persistedToken = tokenRepository.findByToken(token);
        if (persistedToken.isEmpty() && persistedToken.get().getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid token");
        }
        final User user = persistedToken.get().getUser();
        user.setEnabled(true);

    }

}
