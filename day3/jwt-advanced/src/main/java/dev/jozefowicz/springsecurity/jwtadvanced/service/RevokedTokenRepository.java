package dev.jozefowicz.springsecurity.jwtadvanced.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class RevokedTokenRepository {

    private final List<RevokedToken> tokens = new CopyOnWriteArrayList<>();

    public void add(String token, long expirationDate) {
        tokens.add(RevokedToken.of(token, expirationDate));
    }

    public boolean isTokenRevoked(String token) {
        return tokens.stream().anyMatch(revokedToken -> revokedToken.getToken().equals(token));
    }

    public List<RevokedToken> entities() {
        return tokens;
    }

    static final class RevokedToken {
        private final String token;
        private final long expirationDate;

        private RevokedToken(String token, long expirationDate) {
            this.token = token;
            this.expirationDate = expirationDate;
        }

        public String getToken() {
            return token;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        public static RevokedToken of(String token, long expirationDate) {
            return new RevokedToken(token, expirationDate);
        }
    }

    public void removeOutdated() {
        long currentTimestamp = System.currentTimeMillis();
        entities().stream().filter(revokedToken -> revokedToken.getExpirationDate() < currentTimestamp).forEach(revokedToken -> entities().remove(revokedToken));
    }

}
