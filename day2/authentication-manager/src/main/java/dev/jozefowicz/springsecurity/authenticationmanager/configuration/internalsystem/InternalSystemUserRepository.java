package dev.jozefowicz.springsecurity.authenticationmanager.configuration.internalsystem;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public final class InternalSystemUserRepository {

    private final List<InternalUser> internalUsers = new CopyOnWriteArrayList<>();

    public InternalSystemUserRepository() {
        internalUsers.add(InternalUser.of("agx102030", "password"));
        internalUsers.add(InternalUser.of("agx102031", "password"));
        internalUsers.add(InternalUser.of("agx102032", "password"));
    }

    public Optional<InternalUser> findByUsername(final String username) {
        return internalUsers.stream().filter(internalUser -> internalUser.getUsername().equals(username)).findFirst();
    }

    public static final class InternalUser {
        final String username;
        final String password;

        private InternalUser(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public static final InternalUser of(String username, String password){
            return new InternalUser(username, password);
        }
    }
}
