package com.codegym.service;

import com.codegym.entity.User;

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    int Count = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("----------------Menu------------------");
            System.out.println("1: add new book");
            System.out.println("2: update book status");
            System.out.println("3: delete book");
            System.out.println("0: exit");
            System.out.print("Enter your choice :");
            int choice = Integer.parseInt(scanner.nextLine());
            do {
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        updateBook();
                        break;
                    case 3:
                        deleteBook();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }} while (choice != 0);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, enter a number");
            scanner.nextLine();
        }
    }
    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title : ");
        String title = scanner.nextLine();
        System.out.print("Enter author : ");
        String author = scanner.nextLine();
        
    }
    public static void updateBook() {

    }
    public static void deleteBook() {

    }
    private static List<User> loadUsers() throws IOException {
        File file = new File("D:\\khang\\c0624g1Module2\\Project\\src\\com\\codegym\\storage\\book.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<User> listBooks = new ArrayList<>();

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
            String userName = tokens[0];
            String password = tokens[1];
            String email = tokens[2];
        }
        bufferedReader.close();
        return listBooks;
    }

    private static void saveBooks(List<User> listBooks) throws IOException {
        File myFile = new File("D:\\khang\\c0624g1Module2\\Project\\src\\com\\codegym\\storage\\book.txt");
        FileWriter writer = new FileWriter(myFile,true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (User user : listBooks) {
            String data = user.getId() + "," + user.getName() + "," + user.getEmail();
            bufferedWriter.append(data);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}

