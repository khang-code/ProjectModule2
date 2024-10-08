package com.codegym.service;
import com.codegym.entity.User;
import com.codegym.regex.GmailRegex;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice ;
        do{
            System.out.println("----------------Menu------------------");
            System.out.println("1: create new user");
            System.out.println("2: update user");
            System.out.println("3: delete user");
            System.out.println("4: show all users");
            System.out.println("0: exit");
            System.out.print("Enter your choice :");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("exiting ...");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
    } catch (NumberFormatException e) {
            System.out.println("Invalid input, enter a number");
            choice = -1;
            }
        } while (choice != 0);
    }
    public static void createUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;
        do {
            System.out.println("Enter user name (cannot be empty): ");
            userName = scanner.nextLine().trim();
            if (userName.isEmpty()) {
                System.out.println("User name cannot be empty. Please try again.");
            }
        } while (userName.isEmpty());
        do {
            System.out.println("Enter password (cannot be empty): ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Password cannot be empty. Please try again.");
            }
        } while (password.isEmpty());
        String email;
        do {
            System.out.println("Enter email: ");
            email = scanner.nextLine().trim();
            if (!GmailRegex.gmailValidate(email)) {
                System.out.println("Invalid email. Please try again.");
            }
        } while (!GmailRegex.gmailValidate(email));
        List<User> listUsers = loadUsers();
        String id = String.valueOf(listUsers.size() + 1);
        User user = new User(id, userName, password, email);
        listUsers.add(user);
        saveUsers(listUsers);
        System.out.println("User created successfully.");
    }
    public static List<User> updateUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<User> listUsers = loadUsers();
        System.out.println("Enter user name: ");
        String userName = scanner.nextLine();
        List<User> matchingUsers = new ArrayList<>();
        for (User user : listUsers) {
            if (user.getName().contains(userName)) {
                matchingUsers.add(user);
            }
        }
        if (matchingUsers.isEmpty()) {
            System.out.println("User not found");
            return listUsers;
        }
        System.out.println("Matching users: ");
        for (int i = 0; i < matchingUsers.size(); i++) {
            User user = matchingUsers.get(i);
            System.out.println(i + ": " + user.getName() + " (Email: " + user.getEmail() + ")");
        }

        System.out.println("Select the index of the user you want to update:");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
            if (index < 0 || index > listUsers.size()) {
                System.out.println("Invalid index");
                return matchingUsers;
            }
        } catch (NumberFormatException e){
            System.out.println("Invalid index, please try another index");
            return matchingUsers;
        }
        User userToUpdate = matchingUsers.get(index);
        System.out.println("Updating user: " + userToUpdate.getName());
        System.out.println("Enter new name (leave blank to keep current):");
        String newName = scanner.nextLine();
        System.out.println("Enter new email (leave blank to keep current):");
        String newEmail = scanner.nextLine();

        if (!newName.isBlank()) {
            userToUpdate.setName(newName);
        }
        if (!newEmail.isBlank()) {
            userToUpdate.setEmail(newEmail);
        }
        saveUsers(listUsers);
        System.out.println("User successfully updated");
    return listUsers;
    }

    public static void deleteUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<User> listUsers = updateUser();

        System.out.println("Please enter the index of the user you want to delete (0 to " + (listUsers.size() - 1) + "):");
        String searchName = scanner.nextLine();

        List<User> matchingUsers = new ArrayList<>();
        for (User user : listUsers) {
            if (user.getName().contains(searchName)) {
                matchingUsers.add(user);
            }
        }

        if (matchingUsers.isEmpty()) {
            System.out.println("No users found with that name.");
            return;
        }
        System.out.println("Matching users:");
        for (int i = 0; i < matchingUsers.size(); i++) {
            User user = matchingUsers.get(i);
            System.out.println(i + ": " + user.getName() + " (Email: " + user.getEmail() + ")");
        }
        System.out.println("Select the index of the user you want to delete:");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
            if (index < 0 || index > listUsers.size()) {
                System.out.println("Invalid index");
                return;
            }
            User userToDelete = listUsers.get(index);
            listUsers.remove(index);
            saveUsers(listUsers);
            System.out.println("User successfully deleted");
        } catch (NumberFormatException e){
            System.out.println("Invalid index, please try another index");
        }
    }
    private static List<User> loadUsers() throws IOException {
        File file = new File("D:\\khang\\c0624g1Module2\\Project\\src\\com\\codegym\\storage\\user.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<User> listUsers = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty()) {
            continue;
        }
        String[] tokens = line.split(",");
        if (tokens.length < 3) {
            System.out.println("invalid line" + line);
            continue;
        }

        int id = Integer.parseInt(tokens[0]);
        String username = tokens[1];
        String email = tokens[2];
        }
        bufferedReader.close();
        return listUsers;
    }

    private static void saveUsers(List<User> listUsers) throws IOException {
        File myFile = new File("D:\\khang\\c0624g1Module2\\Project\\src\\com\\codegym\\storage\\user.csv");
        FileWriter writer = new FileWriter(myFile,true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (User user : listUsers) {
            String data = user.getId() + "," + user.getName() + "," + user.getEmail();
            bufferedWriter.append(data);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}