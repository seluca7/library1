package controller;

import model.Book;
import model.User;
import presentation.AdminPresentation;
import service.UserService;
import utils.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {
    private final AdminPresentation adminPresentation;
    private final UserService userService;
    private final DataConverter dataConverter;

    public AdminController(AdminPresentation adminPresentation, UserService userService, DataConverter dataConverter ){
        this.adminPresentation = adminPresentation;
        this.userService = userService;
        this.dataConverter = dataConverter;



        this.adminPresentation.addSeeAllUsersActionListener(new SeeAllUsersActionListener());
        this.adminPresentation.addCreateUpdateDeleteActionListener(new CreateUpdateDeleteListener());
        this.adminPresentation.addSearchButtonListener(new SearchButtonListener());

    }

    private class SeeAllUsersActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            List<User> users = userService.findAll();
            Object[][] userData = dataConverter.userToTableData(users);
            String[] usersColumnNames = dataConverter.userToTableColumnNames();
            adminPresentation.refreshUserTable(userData,usersColumnNames);
        }

    }

    private class SearchButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Long id = adminPresentation.getIdSearchValue();
            User user = userService.findById(id);
            List<User> users = new ArrayList<>();
            users.add(user);
            Object[][] userData = dataConverter.userToTableData(users);
            String[] usersColumnNames = dataConverter.userToTableColumnNames();
            adminPresentation.refreshFoundUsersTable(userData,usersColumnNames);

        }
    }

    private class CreateUpdateDeleteListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            if(adminPresentation.comboboxValue().equals("Create")){
                if(adminPresentation.getAdminValue().equals("")||
                adminPresentation.getPasswordValue().equals("")||
                adminPresentation.getUsernameValue().equals("")){
                    System.out.println("Campuri necompletate");
                }
                else {
                    User user = new User();
                    user.setUsername(adminPresentation.getUsernameValue());
                    user.setPassword(adminPresentation.getPasswordValue());
                    user.setAdmin(Boolean.valueOf(adminPresentation.getAdminValue()));

                    user = userService.create(user);
                    System.out.println("Utilizator adaugat: " + user.toString());
                }
            }
            if(adminPresentation.comboboxValue().equals("Update")){
                if(adminPresentation.getAdminValue().equals("")||
                        adminPresentation.getPasswordValue().equals("")||
                        adminPresentation.getUserIdValue().equals("")||
                        adminPresentation.getUsernameValue().equals("")){
                    System.out.println("Campuri necompletate");
                }
                else {
                    User user = new User();
                    user.setUsername(adminPresentation.getUsernameValue());
                    user.setPassword(adminPresentation.getPasswordValue());
                    user.setAdmin(Boolean.valueOf(adminPresentation.getAdminValue()));
                    user.setId(Long.parseLong(adminPresentation.getUserIdValue()) );

                    user = userService.update(user);
                    System.out.println("Utilizator updatat: " + user.toString());
                }
            }
            if(adminPresentation.comboboxValue().equals("Delete")){
                if(adminPresentation.getUserIdValue().equals("")){
                    System.out.println("campul de id nu este completat");
                }
                else {
                    Long id = Long.parseLong(adminPresentation.getUserIdValue());
                    if (userService.deleteById(id)){
                        System.out.println("Utilizatorul cu id-ul " + id + " a fost sters cu succes");
                    }
                    else {
                        System.out.println("Stergerea a esuat");

                    }                }
            }
        }
    }

}
