package repository.impl;

import model.ShoppingBasket;
import model.ShoppingBasketBook;
import model.User;
import repository.ShoppingBasketBookRepository;
import repository.ShoppingBasketRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketRepositoryImpl implements ShoppingBasketRepository {
    private final JDBConnectionWrapper jdbConnectionWrapper;
    private final ShoppingBasketBookRepository shoppingBasketBookRepository;

    public ShoppingBasketRepositoryImpl(JDBConnectionWrapper jdbConnectionWrapper,
                                        ShoppingBasketBookRepository shoppingBasketItemRepository) {
        this.jdbConnectionWrapper = jdbConnectionWrapper;
        this.shoppingBasketBookRepository = shoppingBasketItemRepository;
    }

    @Override
    public List<ShoppingBasket> findAll() {
        Connection connection = jdbConnectionWrapper.getConnection();
        List<ShoppingBasket> shoppingBaskets = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket AS s " +
                            "INNER JOIN user AS u ON s.user_id = u.id");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                shoppingBaskets.add(toShoppingBasket(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingBaskets;
    }

    @Override
    public ShoppingBasket findById(Long id) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_basket AS s " +
                            "INNER JOIN user AS u ON s.user_id = u.id " +
                            "WHERE s.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return toShoppingBasket(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ShoppingBasket create(ShoppingBasket shoppingBasket) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shopping_basket (user_id) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasket.getUser().getId());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                shoppingBasket.setId(resultSet.getLong(1));
                return shoppingBasket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingBasket update(ShoppingBasket shoppingBasket) {
        Connection connection = jdbConnectionWrapper.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shopping_basket SET user_id=? WHERE id=?",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, shoppingBasket.getUser().getId());
            preparedStatement.setLong(2, shoppingBasket.getId());

            int changedRows = preparedStatement.executeUpdate();

            if (changedRows > 0) {
                return shoppingBasket;
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_basket WHERE id= ?");
            preparedStatement.setLong(1, id);

            int updatedRows = preparedStatement.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private ShoppingBasket toShoppingBasket(ResultSet resultSet) throws SQLException {
        ShoppingBasket shoppingBasket = new ShoppingBasket();
        User user = new User();

        user.setId(resultSet.getLong(3));
        user.setUsername(resultSet.getString(4));
        user.setPassword(resultSet.getString(5));
        user.setAdmin(resultSet.getBoolean(6));

        shoppingBasket.setId(resultSet.getLong(1));
        shoppingBasket.setUser(user);

        List<ShoppingBasketBook> books = shoppingBasketBookRepository.findAllByShoppingBasketId(shoppingBasket.getId());
        shoppingBasket.setBooks(books);

        return shoppingBasket;
    }

}
