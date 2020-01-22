package dev.jozefowicz.springsecurity.methodsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;

@Service
public class DummyService {

    @Secured("ROLE_ADMIN")
    public void admin() {
        System.out.println("only for admin");
    }

    @IsAdmin
    public void admin2() {
        System.out.println("IsAdmin - only for admin");
    }

    @RolesAllowed("ROLE_EDITOR")
    public void editor() {
        System.out.println("only for editor");
    }

    @PreAuthorize("hasRole('READER')")
    public void reader() {
        System.out.println("reader");
    }

    public void anyUser() {
        System.out.println("any user");
    }
}
