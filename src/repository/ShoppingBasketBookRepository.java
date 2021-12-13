package repository;

import model.ShoppingBasketBook;

import java.util.List;

public interface ShoppingBasketBookRepository {
    List<ShoppingBasketBook> findAllByShoppingBasketId(Long shoppingBasketId);

    ShoppingBasketBook findByShoppingBasketIdAndByItemId(Long shoppingBasketId, Long itemId);

    ShoppingBasketBook create(ShoppingBasketBook shoppingBasketItem);

    ShoppingBasketBook update(ShoppingBasketBook shoppingBasketItem);

    boolean deleteById(Long id);
}
