package com.codegym.controller;

import com.codegym.entity.people.Role;
import com.codegym.entity.people.User;
import com.codegym.service.BookService;
import com.codegym.service.UserService;
import com.codegym.service_show.UserServiceShow;

import java.util.HashMap;
import java.util.Map;

public class UserController {

    private final UserService userService;
    private Map<String, User> users = new HashMap<String, User>();
    private UserServiceShow userServiceShow ;
    private UserController userController;
    private BookController bookController;
    private User loggedUser;

    public UserController(UserServiceShow userServiceShow,
                          UserService userService,
                          BookService bookService,
                          UserController userController) {
        this.userServiceShow = userServiceShow;
        this.userService = userService;
        this.bookController = bookController;
    }

    public void start() {
        while(true) {
            if (loggedUser == null) {
                userServiceShow.showTheMenu();
                String choice = userServiceShow.getInput("Your choice: ");
                switch (choice) {
                    case "1" -> registerReader();
                    case "2" -> login();
                    case "0" -> {
                        userServiceShow.showMessage("You are logged in");
                        return;
                    }
                    default -> {userServiceShow.showMessage("Invalid choice");return;}
                }
            } else { handleRoleBasedMenu();}
        }
    }

    private void handleRoleBasedMenu() {
        if (loggedUser.hasRole(Role.ADMIN_USER)) {
            handleAdminMenu();
        } else if (loggedUser.hasRole(Role.SPONSOR_USER)) {
            handleSponsorMenu();
        } else (loggedUser.hasRole(Role.READER_USER) {
            handleReaderMenu();
    }
    }

    private void registerReader() {
        String username = "";
        String password = "";
        String email = "";
        String phone = "";
        while (true) {
            username = userServiceShow.getInput("Username: ");
            }
        }
    }

    private void logout() {
        loggedUser = null;
        userServiceShow.showMessage("You are logged out");
    }
}
