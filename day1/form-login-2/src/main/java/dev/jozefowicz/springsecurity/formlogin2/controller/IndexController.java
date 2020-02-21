package dev.jozefowicz.springsecurity.formlogin2.controller;

import dev.jozefowicz.springsecurity.formlogin2.domain.Book;
import dev.jozefowicz.springsecurity.formlogin2.domain.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    private final BookRepository bookRepository;

    public IndexController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ModelAndView index() {
        final ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", bookRepository.findAll());
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/newBook")
    public String newBook(@ModelAttribute("book") Book book, Model model) {
        bookRepository.persist(book);
        model.addAttribute("books", bookRepository.findAll());
        return "redirect:/";
    }
}
