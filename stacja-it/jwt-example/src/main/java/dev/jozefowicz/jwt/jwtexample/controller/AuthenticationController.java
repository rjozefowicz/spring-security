package dev.jozefowicz.jwt.jwtexample.controller;

import dev.jozefowicz.jwt.jwtexample.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/token")
    public ResponseEntity<Void> login(@RequestBody LoginFormDto dto, HttpServletResponse response) {
        // TODO zuwierzytelnij usera i zwróc w headerze Authorization wartośc tokena. Wykorzystaj beana AuthenticationManagera
            return ResponseEntity.ok().build();
    }

    // TODO - zwroc informacje o zalogowanym userze
    @GetMapping("/me")
    public ResponseEntity<Object> me() {
        return ResponseEntity.ok().build();
    }

}
