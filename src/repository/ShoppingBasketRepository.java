package repository;

import model.ShoppingBasket;

import java.util.List;

public interface ShoppingBasketRepository {
    List<ShoppingBasket> findAll();

    ShoppingBasket findById(Long id);

    ShoppingBasket create(ShoppingBasket shoppingBasket);

    ShoppingBasket update(ShoppingBasket shoppingBasket);

    boolean deleteById(Long id);
}
