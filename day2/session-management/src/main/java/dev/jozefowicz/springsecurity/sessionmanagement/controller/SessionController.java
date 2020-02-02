package dev.jozefowicz.springsecurity.sessionmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/session")
public class SessionController {

    // TODO

    private ModelAndView modelAndView(final String message) {
        final ModelAndView modelAndView = new ModelAndView("session");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
