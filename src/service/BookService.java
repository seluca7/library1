package service;

import model.Book;

import java.util.List;

public interface BookService {
    Book findByTitle(String title);

    Book findById(Long id);

    List<Book> findAll();

    boolean deleteById(Long id);

    Book update(Book book);

    Book create(Book book);

    List<Book> findByMultipleAttributes(String tile, String author, String genre);

    Book save(Book book);
}
