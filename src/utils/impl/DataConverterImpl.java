package utils.impl;

import model.Book;
import model.ShoppingBasket;
import model.User;
import utils.DataConverter;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    public Object[][] itemToTableData(List<Book> books) {
        Object[][] data = new Object[books.size()][5];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = books.get(row).getId();
            data[row][1] = books.get(row).getTitle();
            data[row][2] = books.get(row).getAuthor();
            data[row][3] = books.get(row).getGenre();
            data[row][4] = books.get(row).getPrice();
        }
        return data;
    }

    public Object[][] shoppingBasketToTableData(ShoppingBasket shoppingBasket) {
        Object[][] data = new Object[shoppingBasket.getBooks().size()][4];
        for (int row = 0; row < data.length; row++) {
            data[row][0] = shoppingBasket.getBooks().get(row).getId();
            data[row][1] = shoppingBasket.getBooks().get(row).getBook().getTitle();
            data[row][2] = shoppingBasket.getBooks().get(row).getBook().getPrice();
            data[row][3] = shoppingBasket.getBooks().get(row).getQuantity();
        }
        return data;
    }

    @Override
    public Object[][] userToTableData(List<User> users) {
        Object[][] data = new Object[users.size()][4];
        for(int row = 0; row<data.length;row++){
            data[row][0] = users.get(row).getId();
            data[row][1] = users.get(row).getUsername();
            data[row][2] = users.get(row).getPassword();
            data[row][3] = users.get(row).isAdmin();
        }
        return data;
    }

    @Override
    public String[] userToTableColumnNames() { return new String[]{"Id", "Username", "Password", "Admin"}; }

    public String[] itemToTableColumnNames() {
        return new String[]{"Id", "Title","Author","Genre", "Price"};
    }

    public String[] shoppingBasketToTableColumnNames() {
        return new String[]{"Id", "Title","Price", "Quantity"};
    }


}
