package dev.jozefowicz.springsecurity.sessionmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/expired")
    public ModelAndView expired() {
        return modelAndView("Session expired");
    }

    @GetMapping("/invalid")
    public ModelAndView invalid() {
        return modelAndView("Invalid session");
    }

    private ModelAndView modelAndView(final String message) {
        final ModelAndView modelAndView = new ModelAndView("session");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
