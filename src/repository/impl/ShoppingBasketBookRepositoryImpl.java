package repository.impl;

import model.Book;
import model.ShoppingBasketBook;
import repository.ShoppingBasketBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketBookRepositoryImpl implements ShoppingBasketBookRepository {
    private final JDBConnectionWrapper jdbConnectionWrapper;

    public ShoppingBasketBookRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
    }

    @Override
    public List<ShoppingBasketBook> findAllByShoppingBasketId(Long shoppingBasketId) {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<ShoppingBasketBook> shoppingBasketBooks = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN book AS a ON s.book_id = a.id " +
                            "WHERE shopping_basket_id=?");
            preparedStatement.setLong(1, shoppingBasketId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ShoppingBasketBook shoppingBasketBook = new ShoppingBasketBook();
                Book item = new Book();

                item.setId(resultSet.getLong(5));
                item.setTitle(resultSet.getString(6));
                item.setAuthor(resultSet.getString(7));
                item.setGenre(resultSet.getString(8));
                item.setPrice(resultSet.getDouble(9));

                shoppingBasketBook.setId(resultSet.getLong(1));
                shoppingBasketBook.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketBook.setBook(item);
                shoppingBasketBook.setQuantity(resultSet.getInt(4));

                shoppingBasketBooks.add(shoppingBasketBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingBasketBooks;
    }

    @Override
    public ShoppingBasketBook findByShoppingBasketIdAndByItemId(Long shoppingBasketId, Long itemId) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket_item AS s " +
                            "INNER JOIN book AS a ON s.book_id = a.id " +
                            "WHERE shopping_basket_id=? AND book_id=?");
            preparedStatement.setLong(1, shoppingBasketId);
            preparedStatement.setLong(2, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ShoppingBasketBook shoppingBasketBook = new ShoppingBasketBook();
                Book item = new Book();

                item.setId(resultSet.getLong(5));
                item.setTitle(resultSet.getString(6));
                item.setAuthor(resultSet.getString(7));
                item.setGenre(resultSet.getString(8));
                item.setPrice(resultSet.getDouble(9));

                shoppingBasketBook.setId(resultSet.getLong(1));
                shoppingBasketBook.setShoppingBasketId(resultSet.getLong(2));
                shoppingBasketBook.setBook(item);
                shoppingBasketBook.setQuantity(resultSet.getInt(4));

                return shoppingBasketBook;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketBook create(ShoppingBasketBook shoppingBasketItem) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_basket_item (shopping_basket_id, book_id, quantity) VALUES(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasketItem.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketItem.getBook().getId());
            preparedStatement.setInt(3, shoppingBasketItem.getQuantity());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                shoppingBasketItem.setId(resultSet.getLong(1));
                return shoppingBasketItem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasketBook update(ShoppingBasketBook shoppingBasketItem) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopping_basket_item SET shopping_basket_id=?, book_id=?, quantity=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasketItem.getShoppingBasketId());
            preparedStatement.setLong(2, shoppingBasketItem.getBook().getId());
            preparedStatement.setInt(3, shoppingBasketItem.getQuantity());
            preparedStatement.setLong(4, shoppingBasketItem.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return shoppingBasketItem;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_basket_item WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
