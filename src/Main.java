import controller.AdminController;
import controller.LoginController;
import controller.UserController;
import model.Book;
import model.ShoppingBasket;
import model.ShoppingBasketBook;
import model.User;
import presentation.AdminPresentation;
import presentation.LoginWindow;
import presentation.UserPresentation;
import reports.*;
import repository.BookRepository;
import repository.ShoppingBasketBookRepository;
import repository.ShoppingBasketRepository;
import repository.UserRepository;
import repository.impl.*;
import service.BookService;
import service.ShoppingBasketService;
import service.impl.BookServiceImpl;
import service.impl.ShoppingBasketDecoratorImpl;
import service.impl.ShoppingBasketServiceImpl;
import service.impl.UserServiceImpl;
import utils.DataConverter;
import utils.impl.DataConverterImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String schema = "library";
        JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper(schema);
        UserRepository userRepository = new UserRepositoryImpl(jdbConnectionWrapper);
        BookRepository bookRepository = new BookRepositoryImpl(jdbConnectionWrapper);
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        BookService bookService = new BookServiceImpl(bookRepository);
        ShoppingBasketBookRepository shoppingBasketBookRepository = new ShoppingBasketBookRepositoryImpl(jdbConnectionWrapper);
        ShoppingBasketRepository shoppingBasketRepository = new ShoppingBasketRepositoryImpl(jdbConnectionWrapper,shoppingBasketBookRepository);
        DataConverter dataConverter = new DataConverterImpl();
        ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl(bookRepository,
                shoppingBasketBookRepository,
                shoppingBasketRepository);
        ShoppingBasketService shoppingBasketServiceDecorator = new ShoppingBasketDecoratorImpl(shoppingBasketService);

        LoginController loginController = new LoginController();







    }
}