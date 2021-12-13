package model;

import java.util.List;

public class ShoppingBasket extends EntityObject {
    private User user;
    private List<ShoppingBasketBook> books;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ShoppingBasketBook> getBooks() {
        return books;
    }

    public void setBooks(List<ShoppingBasketBook> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "user=" + user +
                ", books=" + books +
                '}';
    }
}
