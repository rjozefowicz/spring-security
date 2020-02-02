package dev.jozefowicz.springsecurity.sessionmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        final List<UserSessionInformationDTO> userSessionInformation = Collections.emptyList(); // TODO

        modelAndView.addObject("users", userSessionInformation);
        return modelAndView;
    }

    @PostMapping("/invalidate")
    @ResponseBody
    public void invalidateAll() {
        // TODO
    }

    @PostMapping("/invalidate/{sessionId}")
    @ResponseBody
    public void invalidateBySessionId(@PathVariable("sessionId") String sessionId) {
       // TODO
    }

    public static final class UserSessionInformationDTO {
        private final String username;
        private final List<String> sessions;

        private UserSessionInformationDTO(String username, List<String> sessions) {
            this.username = username;
            this.sessions = sessions;
        }

        public String getUsername() {
            return username;
        }

        public List<String> getSessions() {
            return sessions;
        }

        public final static UserSessionInformationDTO of(String username, List<String> sessions) {
            return new UserSessionInformationDTO(username, sessions);
        }
    }

}
