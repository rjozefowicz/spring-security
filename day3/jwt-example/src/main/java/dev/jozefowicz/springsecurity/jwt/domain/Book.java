package dev.jozefowicz.springsecurity.jwt.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class Book {
    String title;
    String author;
    String isbn;
}
