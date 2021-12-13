package service.impl;

import model.Book;
import model.ShoppingBasket;
import model.ShoppingBasketBook;
import repository.BookRepository;
import repository.ShoppingBasketBookRepository;
import repository.ShoppingBasketRepository;
import service.ShoppingBasketService;

import java.util.List;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {
    private final BookRepository bookRepository;
    private final ShoppingBasketBookRepository shoppingBasketBookRepository;
    private final ShoppingBasketRepository shoppingBasketRepository;

    public ShoppingBasketServiceImpl(BookRepository bookRepository,
                                     ShoppingBasketBookRepository shoppingBasketBookRepository,
                                     ShoppingBasketRepository shoppingBasketRepository) {
        this.bookRepository = bookRepository;
        this.shoppingBasketBookRepository = shoppingBasketBookRepository;
        this.shoppingBasketRepository = shoppingBasketRepository;
    }

    @Override
    public void addItem(Long shoppingBasketId, Long itemId, int quantity) {
        Book book = bookRepository.findById(itemId);

        ShoppingBasketBook shoppingBasketBook = shoppingBasketBookRepository.findByShoppingBasketIdAndByItemId(shoppingBasketId, itemId);

        if (shoppingBasketBook != null) {
            shoppingBasketBook.setQuantity(shoppingBasketBook.getQuantity() + quantity);

            shoppingBasketBookRepository.update(shoppingBasketBook);
        } else {
            shoppingBasketBook = new ShoppingBasketBook();
            shoppingBasketBook.setShoppingBasketId(shoppingBasketId);
            shoppingBasketBook.setBook(book);
            shoppingBasketBook.setQuantity(quantity);

            shoppingBasketBookRepository.create(shoppingBasketBook);
        }
    }

    @Override
    public ShoppingBasket findById(Long id) {
        return shoppingBasketRepository.findById(id);
    }

    @Override
    public ShoppingBasket findByUserId(Long userId) {
        return shoppingBasketRepository.findAll()
                .stream()
                .filter(shoppingBasket -> shoppingBasket.getUser().getId().equals(userId))
                .findFirst().get();
    }

    @Override
    public List<ShoppingBasket> findAll() {
        return shoppingBasketRepository.findAll();
    }

    @Override
    public ShoppingBasket create(ShoppingBasket shoppingBasket) {
        return shoppingBasketRepository.create(shoppingBasket);
    }

    @Override
    public ShoppingBasket update(ShoppingBasket shoppingBasket) {
        return shoppingBasketRepository.update(shoppingBasket);
    }

    @Override
    public boolean deleteById(Long id) {
        return shoppingBasketRepository.deleteById(id);
    }
}
