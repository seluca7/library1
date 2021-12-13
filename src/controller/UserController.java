package controller;

import model.Book;
import model.ShoppingBasket;
import model.ShoppingBasketBook;
import model.User;
import presentation.UserPresentation;
import reports.FileType;
import reports.GetReportFactory;
import reports.Report;
import repository.ShoppingBasketBookRepository;
import repository.ShoppingBasketRepository;
import service.BookService;
import service.ShoppingBasketService;
import service.UserService;
import utils.DataConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private final UserPresentation userPresentation;
    private final BookService bookService;
    private final DataConverter dataConverter;
    private final ShoppingBasketService shoppingBasketService;
    private final ShoppingBasketBookRepository shoppingBasketBookRepository;
    private final UserService userService;
    private Double currentTotal;

    public UserController(UserPresentation userPresentation,BookService bookService,
                          DataConverter dataConverter ,
                          ShoppingBasketService shoppingBasketService,
                          ShoppingBasketBookRepository shoppingBasketBookRepository,
                          UserService userService
    ){
        this.userPresentation = userPresentation;
        this.bookService = bookService;
        this.dataConverter = dataConverter;
        this.shoppingBasketService = shoppingBasketService;
        this.shoppingBasketBookRepository = shoppingBasketBookRepository;
        this.userService = userService;
        this.currentTotal = 0.0;

        this.userPresentation.addSeeAllBooksActionListener(new SeeAllBooksActionListener());
        this.userPresentation.addSearchButtonListener(new SearchButtonListener());
        this.userPresentation.addCreateUpdateDeleteActionListener(new CreateUpdateDeleteActionListener());
        this.userPresentation.addAddToBaskettActionListener(new AddToBasketActionListener());
        this.userPresentation.addCreateUpdateDeleteBasketActionListener(new CreateUpdateDeleteBasketActionListener());
        this.userPresentation.addSeeShoppingBasketActionListener(new SeeShoppingBasketActionListener());
        this.userPresentation.addSellActionListener(new SellActionListener());
    }

    private class SellActionListener implements ActionListener{
        GetReportFactory reportFactory = new GetReportFactory();

        public void actionPerformed(ActionEvent e){
            if(userPresentation.getFormat().equals("txt")){
                Report report = reportFactory.getReport(FileType.TXT);
                ShoppingBasket shoppingBasket = new ShoppingBasket();
                shoppingBasket = shoppingBasketService.findById(Long.parseLong(userPresentation.getCurrentBasketId()));
                report.generateReport("Utilizatorul " + shoppingBasket.getUser().getUsername() + " a facut o achizitie in valoare de " + currentTotal);
            }
            else if(userPresentation.getFormat().equals("csv")){
                Report report = reportFactory.getReport(FileType.CSV);
                ShoppingBasket shoppingBasket = new ShoppingBasket();
                shoppingBasket = shoppingBasketService.findById(Long.parseLong(userPresentation.getCurrentBasketId()));
                report.generateReport("Utilizatorul " + shoppingBasket.getUser().getUsername() + " a facut o achizitie in valoare de " + currentTotal);
            }
        }
    }

    private class SeeShoppingBasketActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            if(userPresentation.getCurrentBasketId().equals("")){
                System.out.println("Campuri necompletate");
            }
            else {
                ShoppingBasket shoppingBasket = shoppingBasketService.findById(Long.parseLong(userPresentation.getCurrentBasketId()));
                Object[][] basketData = dataConverter.shoppingBasketToTableData(shoppingBasket);
                String[] columnNames = dataConverter.shoppingBasketToTableColumnNames();
                userPresentation.refreshShoppingBasketTable(basketData,columnNames);

                currentTotal = 0.0;

                for(ShoppingBasketBook b:shoppingBasket.getBooks()){
                    currentTotal+= b.getQuantity()*b.getBook().getPrice();
                }
                userPresentation.setPrice(currentTotal);

            }
        }
    }

    private class CreateUpdateDeleteBasketActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            System.out.println("basket:" + userPresentation.getCRUDBasketId());
            System.out.println("user:" + userPresentation.getCRUDUserId());
            if (userPresentation.getCRUDBasketComboBox().equals("Create")) {
                System.out.println("create");
                if (userPresentation.getCRUDUserId().equals("")) {
                    System.out.println("Campuri necompletate");
                } else {
                    ShoppingBasket shoppingBasket = new ShoppingBasket();
                    User user = userService.findById(Long.parseLong(userPresentation.getCRUDUserId()));
                    shoppingBasket.setUser(user);
                    shoppingBasket = shoppingBasketService.create(shoppingBasket);
                    System.out.println("Shopping basket nou creat:" + shoppingBasket.toString());
                }
            }
            if (userPresentation.getCRUDBasketComboBox().equals("Update")) {
                System.out.println("update");
                if (userPresentation.getCRUDUserId().equals("") ||
                        userPresentation.getCRUDBasketId().equals("")) {
                    System.out.println("Campuri necompletate");
                } else {
                    ShoppingBasket shoppingBasket = new ShoppingBasket();
                    User user = userService.findById(Long.parseLong(userPresentation.getCRUDUserId()));
                    shoppingBasket.setUser(user);
                    shoppingBasket.setId(Long.parseLong(userPresentation.getCRUDBasketId()));
                    shoppingBasket = shoppingBasketService.update(shoppingBasket);
                    System.out.println("Shopping basket updatat:" + shoppingBasket.toString());
                }
            }
            if (userPresentation.getCRUDBasketComboBox().equals("Delete")) {
                System.out.println("delete");
                if (userPresentation.getCRUDBasketId().equals("")) {
                    System.out.println("Campuri necompletate");
                } else {
                    if (shoppingBasketService.deleteById(Long.parseLong(userPresentation.getCRUDBasketId()))) {
                        System.out.println("Cosul cu id-ul " + Long.parseLong(userPresentation.getCRUDBasketId()) + " a fost sters");
                    } else {
                        System.out.println("stergerea a esuat");
                    }
                }
            }
        }
    }

    private class AddToBasketActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            if(userPresentation.getAddToBasketBookId().equals("")||
            userPresentation.getAddToBasketBasketId().equals("")||
            Integer.parseInt(userPresentation.getAddToBasketQuantity())==0){
                System.out.println("campuri necompletate sau cantitate invalida");
            }
            else {
                shoppingBasketService.addItem(Long.parseLong(userPresentation.getAddToBasketBasketId()),
                        Long.parseLong(userPresentation.getAddToBasketBookId()),
                        Integer.parseInt(userPresentation.getAddToBasketQuantity()));
            }
        }
    }

    private class CreateUpdateDeleteActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            if(userPresentation.comboboxValue().equals("Create")){
                if(     userPresentation.getAuthor().equals("")||
                        userPresentation.getGenre().equals("")||
                        userPresentation.getPriceString().equals("")||
                        userPresentation.getTitle().equals("")){
                    System.out.println("Campuri necompletate");
                }
                else {
                    Book book = new Book();
                    book.setTitle(userPresentation.getTitle());
                    book.setAuthor(userPresentation.getAuthor());
                    book.setGenre(userPresentation.getGenre());
                    book.setPrice(userPresentation.getPrice());

                    book = bookService.create(book);
                    System.out.println("Carte adaugata: " + book.toString());
                }
            }
            if(userPresentation.comboboxValue().equals("Update")){
                if(     userPresentation.getAuthor().equals("")||
                        userPresentation.getGenre().equals("")||
                        userPresentation.getPriceString().equals("")||
                        userPresentation.getIdString().equals("")||
                        userPresentation.getTitle().equals("")){
                    System.out.println("Campuri necompletate");
                }
                else {
                    Book book = new Book();
                    book.setTitle(userPresentation.getTitle());
                    book.setAuthor(userPresentation.getAuthor());
                    book.setGenre(userPresentation.getGenre());
                    book.setPrice(userPresentation.getPrice());
                    book.setId(userPresentation.getId());

                    book = bookService.update(book);
                    System.out.println("Carte updatata: " + book.toString());
                }
            }
            if(userPresentation.comboboxValue().equals("Delete")){
                if(userPresentation.getIdString().equals("")){
                    System.out.println("campul de id nu este completat");
                }
                else {
                    Long id = userPresentation.getId();
                    if (bookService.deleteById(id)){
                        System.out.println("Cartea cu id-ul " + id + " a fost stearsa cu succes");
                    }
                    else {
                        System.out.println("Stergerea a esuat");

                    }                }
            }
        }
    }

    private class SeeAllBooksActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            List<Book> books = bookService.findAll();
            Object[][] bookData = dataConverter.itemToTableData(books);
            String[] usersColumnNames = dataConverter.itemToTableColumnNames();
            userPresentation.refreshBooksTable(bookData,usersColumnNames);
        }

    }

    private class SearchButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e){
            String title = userPresentation.getSearchTitle();
            String author = userPresentation.getSearchAuthor();
            String genre = userPresentation.getSearchGenre();

            List<Book> books = bookService.findByMultipleAttributes(title,author,genre);

            Object[][] bookData = dataConverter.itemToTableData(books);
            String[] usersColumnNames = dataConverter.itemToTableColumnNames();
            userPresentation.refreshFoundBooksTable(bookData,usersColumnNames);
        }

    }
}
