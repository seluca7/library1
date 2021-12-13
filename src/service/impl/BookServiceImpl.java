package service.impl;

import model.Book;
import repository.BookRepository;
import repository.impl.BookRepositoryImpl;
import service.BookService;

import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public Book create(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public List<Book> findByMultipleAttributes(String title, String author, String genre) {
        List<Book> books = this.findAll();


        List<Book> filteredBooks = books.stream()
                    .filter( u -> (title.equals(""))|| title.equals(null)|| u.getTitle().equals(title))
                    .filter( u -> author.equals("") || author.equals(null) || u.getAuthor().equals(author))
                    .filter( u ->  genre.equals("") || genre.equals(null) || u.getGenre().equals(genre))
                    .collect(Collectors.toList());
        return filteredBooks;
    }

    @Override
    public Book save(Book book) {
        if(book.getId()!=null){
            System.out.println("book updated");
            return bookRepository.update(book);
        }
        else{
            System.out.println("book created");
            return bookRepository.create(book);
        }
    }
}
