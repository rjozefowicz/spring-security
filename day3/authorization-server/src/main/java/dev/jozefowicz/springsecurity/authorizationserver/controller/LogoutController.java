package dev.jozefowicz.springsecurity.authorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/revoke")
public class LogoutController {

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @GetMapping
    public void revoke(@RequestHeader("Authorization") String authorizationHeader) {
        defaultTokenServices.revokeToken(authorizationHeader.replace("Bearer ", ""));
    }

}
