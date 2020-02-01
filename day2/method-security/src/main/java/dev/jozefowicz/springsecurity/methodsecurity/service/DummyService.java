package dev.jozefowicz.springsecurity.methodsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class DummyService {

    @Secured("TODO")
    public void admin() {
        System.out.println("only for admin");
    }

    @Secured("TODO")
    public void admin2() {
        System.out.println("IsAdmin - only for admin");
    }

    @Secured("TODO")
    public void editor() {
        System.out.println("only for editor");
    }
    @Secured("TODO")
    public void reader() {
        System.out.println("reader");
    }
    @Secured("TODO")
    public void anyUser() {
        System.out.println("any user");
    }
}
