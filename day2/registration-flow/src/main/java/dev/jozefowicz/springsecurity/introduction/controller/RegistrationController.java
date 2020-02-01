package dev.jozefowicz.springsecurity.introduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @GetMapping("/signin")
    public ModelAndView signIn() {
        return new ModelAndView("signin");
    }

    @GetMapping("/signup")
    public ModelAndView signUp() {
        final ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("form", new SignUpForm());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView performSingUp(@ModelAttribute("form") SignUpForm signUpForm) {
        final ModelAndView modelAndView = new ModelAndView("signin");
        modelAndView.addObject("message", "Please check your email and verify your account");
        return modelAndView;
    }

    @GetMapping("/verify")
    public ModelAndView verify(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        final ModelAndView modelAndView = new ModelAndView("redirect:/signin");
        redirectAttributes.addFlashAttribute("message", "Account verified, you can sign in now");
        return modelAndView;
    }

}
