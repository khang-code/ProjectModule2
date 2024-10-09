package com.codegym.service_show;
import com.codegym.entity.people.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserServiceShow {
    private final Scanner scanner = new Scanner(System.in);
    public UserServiceShow() {
    }
    public String getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
    public boolean confirm(String message) {
        System.out.print(message + ("yes/no: "));
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
    public void showMenu(String title, List<String> options) {
        System.out.println("==============" + title + "===============");
        for (int i = 0; i < options.size();i++){
            System.out.println((i+1) + ". " + options.get(i));
        }
    }
    public void showTheMenu(){
        List<String> options = new ArrayList<>();
        options.add("Register new user");
        options.add("Login");
        options.add("Exit");
        showMenu("Welcome to my library", options);
    }
    public void showAdminMenu() {
        List<String> options = new ArrayList<>();
        options.add("User check");
        options.add("Book check");
        options.add("Return");
        showMenu("Librarian works", options);
    }
    public void showSponsorMenu() {
        List<String> options = new ArrayList<>();
        options.add("giving books");
        options.add("check books had given");
        options.add("borrow books");
        options.add("return books");
        options.add("return");
        showMenu("Sponsors Menu", options);
    }
    public void showReaderMenu(){
        List<String> options = new ArrayList<>();
        options.add("borrow books");
        options.add("return books");
        options.add("return");
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
    public void displayUsers(Map<String, User> users) {
        if (users.isEmpty()) {
            System.out.println("There are no users");
            return;
        }
        System.out.println("User List");
        for (User user : users.values()) {
            System.out.println(user);
        }
    }
}