package dev.jozefowicz.springsecurity.jwt.controller;

import dev.jozefowicz.springsecurity.jwt.domain.User;
import dev.jozefowicz.springsecurity.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/me")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * TODO Endpoint powinien zwrócić UserDTO na podstawie aktualnego principala. Brakujące dane powinien pobrać z repozytorium
     * @return
     */
    @GetMapping
    public Object me() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }



}
