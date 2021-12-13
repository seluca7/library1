package repository.impl;

import model.Book;
import repository.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    JDBConnectionWrapper jdbConnectionWrapper;
    public BookRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper){
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }
    @Override
    public Book findByTitle(String title) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE title=?");
            preparedStatement.setString(1,title);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setGenre(resultSet.getString(4));
                book.setPrice(resultSet.getDouble(5));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id=?");
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setGenre(resultSet.getString(4));
                book.setPrice(resultSet.getDouble(5));

                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();

                book.setId(resultSet.getLong(1));
                book.setTitle(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setGenre(resultSet.getString(4));
                book.setPrice(resultSet.getDouble(5));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book update(Book book) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET title=?, author=?, genre=?, price=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setLong(5, book.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return book;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book create(Book book) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (title, author, genre, price) VALUES(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4,book.getPrice());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                book.setId(resultSet.getLong(1));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
