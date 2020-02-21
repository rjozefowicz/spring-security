package dev.jozefowicz.springsecurity.formlogin2.domain;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BookRepository {
    private List<Book> books = new CopyOnWriteArrayList<>();

    public BookRepository() {
        books.add(new Book("Dwunasta planeta", "Zecharia Sitchin", "aabbccdd"));
        books.add(new Book("Schody do nieba", "Zecharia Sitchin", "aabbccee"));
        books.add(new Book("Wojny bogow i ludzi", "Zecharia Sitchin", "eeddffee"));
        books.add(new Book("Zaginione krolestwa", "Zecharia Sitchin", "uujjhhgg"));
    }

    public List<Book> findAll() {
        return List.copyOf(books);
    }

    public void persist(Book book) {
        books.add(book);
    }
}
