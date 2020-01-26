package dev.jozefowicz.springsecurity.introduction.domain;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BookRepository {

    private List<Book> books = new CopyOnWriteArrayList<>();

    public BookRepository() {
        books.add(new Book("Dwunasta planeta", "Zecharia Sitchin", "aabbccdd"));
        books.add(new Book("Schody do nieba", "Zecharia Sitchin", "aabbccee"));
    }

    public List<Book> findAll() {
        return Collections.unmodifiableList(books);
    }

    public void persist(Book book) {
        books.add(book);
    }

}
