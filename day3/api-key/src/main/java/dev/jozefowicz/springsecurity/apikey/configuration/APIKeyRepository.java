package dev.jozefowicz.springsecurity.apikey.configuration;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class APIKeyRepository {

    private final List<APIKey> keys = new CopyOnWriteArrayList<>();

    public enum RoleEnum {
        ADMIN, USER;
    }

    public APIKeyRepository() {
        keys.add(new APIKey("0000", "admin1", RoleEnum.ADMIN));
        keys.add(new APIKey("1111", "admin2", RoleEnum.USER));
        keys.add(new APIKey("2222", "admin3", RoleEnum.USER));
    }

    public Optional<APIKey> findByKey(final String key) {
        return keys.stream().filter(apiKey -> apiKey.getKey().equals(key)).findFirst();
    }

    public static class APIKey {
        private final String key;
        private final String username;

        private final RoleEnum role;

        public APIKey(String key, String username, RoleEnum role) {
            this.key = key;
            this.username = username;
            this.role = role;
        }

        public String getKey() {
            return key;
        }

        public String getUsername() {
            return username;
        }

        public RoleEnum getRole() {
            return role;
        }
    }

}