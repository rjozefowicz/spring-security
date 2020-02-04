package dev.jozefowicz.springsecurity.jwt.domain;

public class Book {
    private String title;
    private String author;
    private String isbn;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static Book of(String title, String author, String isbn) {
        Book book = new Book();
        book.title = title;
        book.author = author;
        book.isbn = isbn;
        return book;
    }
}
