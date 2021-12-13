package repository;

import java.util.List;
import model.Book;

public interface BookRepository {
    Book findByTitle(String title);

    Book findById(Long id);

    List<Book> findAll();

    boolean deleteById(Long id);

    Book update(Book book);

    Book create(Book book);
}
