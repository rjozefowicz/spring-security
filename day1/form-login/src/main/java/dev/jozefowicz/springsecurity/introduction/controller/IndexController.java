package dev.jozefowicz.springsecurity.introduction.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("/portal")
    public ModelAndView index() {
        final ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/invalidLogin")
    public ModelAndView invalidLogin() {
        return new ModelAndView("invalidLogin");
    }

}
