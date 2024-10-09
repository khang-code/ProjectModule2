package com.codegym.service_show;

import com.codegym.entity.books.Book;
import com.codegym.entity.books.Category;
import com.codegym.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServiceShow {
    private UserServiceShow userServiceShow;
    private Scanner scanner = new Scanner(System.in);
    private BookService bookService;

    public BookServiceShow(UserServiceShow userServiceShow, BookService bookService) {
        this.userServiceShow = userServiceShow;
        this.bookService = bookService;
    }

    public void showCategory() {
        List<String> options = new ArrayList<>();
        options.add(Category.NEW_CATEGORY);
        options.add(Category.OLD_CATEGORY);
        options.add("All Books");
        options.add("Return");
        userServiceShow.showMenu("All Options", options);
    }

    public void manageProduct() {
        List<String> options = new ArrayList<>();
        options.add("add new");
        options.add("edit");
        options.add("delete");
        options.add("show library");
        options.add("return");
        userServiceShow.showMenu("Manage Book", options);
    }
    public String inputBookName() {
        String name;
        do {
            System.out.println("Enter the book name: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Enter the book name: ");
            }

        } while (name.trim().isEmpty());
        return name;
    }
    public String inputBookDescription() {
        System.out.println("Enter the book description: ");
        return scanner.nextLine();
    }
    public void displayBookDetails(int productId) {
        int book = bookService.getBookById(productId);
    }
}
