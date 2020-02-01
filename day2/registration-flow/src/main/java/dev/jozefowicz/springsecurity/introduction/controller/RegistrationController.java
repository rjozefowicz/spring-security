package dev.jozefowicz.springsecurity.introduction.controller;

import dev.jozefowicz.springsecurity.introduction.domain.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private SignUpService signUpService;

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
        signUpService.signUp(signUpForm.getEmail(), signUpForm.getPassword());
        modelAndView.addObject("message", "Please check your email and verify your account");
        return modelAndView;
    }

    @GetMapping("/verify")
    public ModelAndView verify(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
        final ModelAndView modelAndView = new ModelAndView("redirect:/signin");
        signUpService.verify(token);
        redirectAttributes.addFlashAttribute("message", "Account verified, you can sign in now");
        return modelAndView;
    }

}
