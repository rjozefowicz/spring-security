package dev.jozefowicz.springsecurity.sessionmanagement.controller;

import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class IndexController {

    private final SessionRegistry sessionRegistry;

    public IndexController(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        final List<UserSessionInformationDTO> userSessionInformation = sessionRegistry
                .getAllPrincipals() // User from Spring Security userdetails package
                .stream()
                .map(u -> {
                    User user = (User) u;
                    return UserSessionInformationDTO.of(user.getUsername(), sessionRegistry.getAllSessions(u, false).size());
                })
                .filter(u -> u.getSessionCount() > 0)
                .collect(Collectors.toList());
        modelAndView.addObject("users", userSessionInformation);
        return modelAndView;
    }

    @PostMapping("/invalidate")
    public void invalidateAll() {
        sessionRegistry.getAllPrincipals().forEach(u -> {
            sessionRegistry.getAllSessions(u, false).forEach(sessionInformation -> {
                sessionInformation.expireNow();
            });
        });
    }

    @PostMapping("/invalidate/{sessionId}")
    public void invalidateBySessionId(@PathVariable("sessionId") String sessionId) {
        sessionRegistry.getSessionInformation(sessionId).expireNow();
    }

    public static final class UserSessionInformationDTO {
        private final String username;
        private final int sessionCount;

        private UserSessionInformationDTO(String username, int sessionCount) {
            this.username = username;
            this.sessionCount = sessionCount;
        }

        public String getUsername() {
            return username;
        }

        public int getSessionCount() {
            return sessionCount;
        }

        public final static UserSessionInformationDTO of(String username, int sessionCount) {
            return new UserSessionInformationDTO(username, sessionCount);
        }
    }

}
