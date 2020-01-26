package dev.jozefowicz.springsecurity.introduction.controller;

import dev.jozefowicz.springsecurity.introduction.domain.Book;
import dev.jozefowicz.springsecurity.introduction.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/portal")
public class IndexController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ModelAndView index() {
        final ModelAndView modelAndView = new ModelAndView("portal");
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("books", bookRepository.findAll());
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book, Model model) {
        bookRepository.persist(book);
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/portal";
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
