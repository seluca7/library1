package service.impl;

import model.ShoppingBasket;
import service.ShoppingBasketService;

import java.util.List;

public class ShoppingBasketDecoratorImpl implements ShoppingBasketService {
    private final ShoppingBasketService shoppingBasketService;

    public ShoppingBasketDecoratorImpl(ShoppingBasketService shoppingBasketService) {
        this.shoppingBasketService = shoppingBasketService;
    }

    @Override
    public void addItem(Long shoppingBasketId, Long itemId, int quantity) {
        shoppingBasketService.addItem(shoppingBasketId, itemId, quantity);

        System.out.println("s-a adaugat in cos id: " + shoppingBasketId +
                " " + quantity +" bucati din articolul cu id: " + itemId);
    }

    @Override
    public ShoppingBasket findById(Long id) {
        return shoppingBasketService.findById(id);
    }

    @Override
    public ShoppingBasket findByUserId(Long userId) {
        return shoppingBasketService.findByUserId(userId);
    }

    @Override
    public List<ShoppingBasket> findAll() {
        return shoppingBasketService.findAll();
    }

    @Override
    public ShoppingBasket create(ShoppingBasket shoppingBasket) {
        return shoppingBasketService.create(shoppingBasket);
    }

    @Override
    public ShoppingBasket update(ShoppingBasket shoppingBasket) {
        return shoppingBasketService.update(shoppingBasket);
    }

    @Override
    public boolean deleteById(Long id) {
        return shoppingBasketService.deleteById(id);
    }
}
