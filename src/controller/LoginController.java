package controller;

import model.User;
import presentation.AdminPresentation;
import presentation.LoginWindow;
import presentation.UserPresentation;
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    final String schema = "library";
    private JDBConnectionWrapper jdbConnectionWrapper = new JDBConnectionWrapper(schema);
    private UserRepository userRepository = new UserRepositoryImpl(jdbConnectionWrapper);
    private BookRepository bookRepository = new BookRepositoryImpl(jdbConnectionWrapper);
    private UserServiceImpl userService = new UserServiceImpl(userRepository);
    private BookService bookService = new BookServiceImpl(bookRepository);
    private ShoppingBasketBookRepository shoppingBasketBookRepository = new ShoppingBasketBookRepositoryImpl(jdbConnectionWrapper);
    private ShoppingBasketRepository shoppingBasketRepository = new ShoppingBasketRepositoryImpl(jdbConnectionWrapper, shoppingBasketBookRepository);
    private DataConverter dataConverter = new DataConverterImpl();
    private ShoppingBasketService shoppingBasketService = new ShoppingBasketServiceImpl(bookRepository,
            shoppingBasketBookRepository,
            shoppingBasketRepository);
    private ShoppingBasketService shoppingBasketServiceDecorator = new ShoppingBasketDecoratorImpl(shoppingBasketService);
    private AdminPresentation adminPresentation = new AdminPresentation();
    private UserPresentation userPresentation = new UserPresentation();
    private LoginWindow loginWindow = new LoginWindow();

    AdminController adminController = new AdminController(adminPresentation, userService, dataConverter);
    UserController userController = new UserController(userPresentation,
            bookService,
            dataConverter,
            shoppingBasketServiceDecorator,
            shoppingBasketBookRepository,
            userService);

    public LoginController()

    {
        loginWindow.addLoginActionListener(new LoginActionListener());
    }

    private class LoginActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String username = loginWindow.getUsername();
            String password = loginWindow.getPassword();
            User user = userService.login(username,password);

            if(user.equals(null)){
                System.out.println("login failed");
            }
            else {
                if (user.isAdmin()) {
                    adminPresentation.setVisible(true);
                } else {
                    userPresentation.setVisible(true);
                }
            }

        }
    }


}
