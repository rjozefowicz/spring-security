package dev.jozefowicz.springsecurity.jaas.configuration;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class TestLoginModule implements LoginModule {

    private String password;
    private String username;
    private Subject subject;

    private static Map<String, String> USER_PASSWORDS = new HashMap<>();

    static {
        USER_PASSWORDS.put("jan", "kowalski");
        USER_PASSWORDS.put("stefan", "kisielewski");
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;

        try {
            NameCallback nameCallback = new NameCallback("prompt");
            PasswordCallback passwordCallback = new PasswordCallback("prompt",false);

            callbackHandler.handle(new Callback[] { nameCallback,passwordCallback });

            this.password = new String(passwordCallback.getPassword());
            this.username = nameCallback.getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login() throws LoginException {
        if (!USER_PASSWORDS.containsKey(username) || !USER_PASSWORDS.get(username).equals(password)) {
            throw new LoginException("invalid credentials");
        }
        final JaasPrincipal jaasPrincipal = new JaasPrincipal();
        jaasPrincipal.name = username;
        subject.getPrincipals().add(jaasPrincipal);
        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }

    public static class JaasPrincipal implements Principal {

        private String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean implies(Subject subject) {
            return false;
        }
    }

}
