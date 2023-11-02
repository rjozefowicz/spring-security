package dev.jozefowicz.jwt.jwtexample.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static dev.jozefowicz.jwt.jwtexample.model.Users.*;
import static dev.jozefowicz.jwt.jwtexample.model.Users.anita;

@Component
public class Repository {

    public static List<Organization> organizations = new ArrayList<>(List.of(
            new Organization(1l, "ACME Organization"),
            new Organization(2l, "Massive Dynamics"),
            new Organization(3l, "Capsule Corporation"))
    );
    public static List<OrganizationUser> organizationUsers = new ArrayList<>(List.of(
            new OrganizationUser(1l, radek.getUsername()),
            new OrganizationUser(1l, jan.getUsername()),
            new OrganizationUser(2l, michal.getUsername()),
            new OrganizationUser(2l, aniela.getUsername()),
            new OrganizationUser(3l, weronika.getUsername()),
            new OrganizationUser(3l, anita.getUsername())
    ));

}
