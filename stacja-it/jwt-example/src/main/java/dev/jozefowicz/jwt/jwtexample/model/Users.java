package dev.jozefowicz.jwt.jwtexample.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class Users {

    public static final UserDetails radek = User.withDefaultPasswordEncoder().username("radek@example.com").password("TopSecret").roles("ADMIN").build();
    public static final UserDetails jan = User.withDefaultPasswordEncoder().username("jan@example.com").password("TopSecret1").roles("USER").build();
    public static final UserDetails michal = User.withDefaultPasswordEncoder().username("michal@example.com").password("TopSecret2").roles("ADMIN").build();
    public static final UserDetails aniela = User.withDefaultPasswordEncoder().username("aniela@example.com").password("TopSecret3").roles("USER").build();
    public static final UserDetails weronika = User.withDefaultPasswordEncoder().username("weronika@example.com").password("TopSecret4").roles("USER").build();
    public static final UserDetails anita = User.withDefaultPasswordEncoder().username("anita@example.com").password("TopSecret5").roles("ADMIN").build();
    public static final List<UserDetails> users = List.of(
            radek,
            jan,
            michal,
            aniela,
            weronika,
            anita
    );

}
