package utils;

import model.Book;
import model.ShoppingBasket;
import model.User;

import java.util.List;

public interface DataConverter {
    Object[][] itemToTableData(List<Book> books);

    Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket);

    Object[][] userToTableData(List<User> users);

    String[] itemToTableColumnNames();

    String[] shoppingBasketToTableColumnNames();

    String[] userToTableColumnNames();
}
