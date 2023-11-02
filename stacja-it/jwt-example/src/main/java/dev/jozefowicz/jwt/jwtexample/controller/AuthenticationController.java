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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        try {
            var authentication = authenticationManager.authenticate(authenticationToken);
            response.addHeader("Authorization", jwtService.generateToken(authentication));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(Map.of("user", authentication.getName()));
    }

}
