package service;

import model.ShoppingBasket;

import java.util.List;

public interface ShoppingBasketService {
    void addItem(Long shoppingBasketId, Long itemId, int quantity);

    ShoppingBasket findById(Long id);

    ShoppingBasket findByUserId(Long userId);

    List<ShoppingBasket> findAll();

    ShoppingBasket create(ShoppingBasket shoppingBasket);

    ShoppingBasket update(ShoppingBasket shoppingBasket);

    boolean deleteById(Long id);
}
