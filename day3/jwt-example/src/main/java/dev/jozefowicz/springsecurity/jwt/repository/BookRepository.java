package dev.jozefowicz.springsecurity.jwt.repository;

import dev.jozefowicz.springsecurity.jwt.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class BookRepository implements Repository<Book> {

    private List<Book> books = new CopyOnWriteArrayList<>();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void persist(Book book) {
        books.add(book);
    }

    public List<Book> findByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equalsIgnoreCase(isbn)).collect(Collectors.toList());
    }
}
