package com.codegym.service;

import com.codegym.entity.books.Book;
import com.codegym.entity.books.Category;
import com.codegym.service_show.UserServiceShow;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    private static final String BOOK_FILE_PATH = "src/com/codegym/books.csv";

    private final UserServiceShow userServiceShow;
    private Map<String, Book> books = new HashMap<>();
    private final Map<String, Category> categories = new HashMap<>();

    // Constructor
    public BookService(UserServiceShow userServiceShow) {
        this.userServiceShow = userServiceShow;
        this.books = loadBooksFromFile();
    }

    // Check if a book ID exists
    public boolean isBookIdTaken(String bookId) {
        return books.containsKey(bookId);
    }

    // Register a new book
    public void registerBook(String bookId, String name, String type, double price, int quantity, String description) {
        if (isBookIdTaken(bookId)) {
            userServiceShow.showMessage("Book ID is already in use. Please choose another.");
        } else {
            Book newBook = new Book(Integer.parseInt(bookId), name, type, price, quantity, description);
            books.put(bookId, newBook);
            saveBooksToFile();
            userServiceShow.showMessage("Book registered successfully.");
        }
    }

    // Add a book to a category
    public void addBookToCategory(String bookId, String categoryName) {
        Book book = books.get(bookId);
        if (book == null) {
            userServiceShow.showMessage("No such book found.");
            return;
        }

        Category category = categories.computeIfAbsent(categoryName, Category::new);
        category.addBookToCategory(book);
        saveBooksToFile();
        userServiceShow.showMessage("Book added to category.");
    }

    // Remove a book by ID
    public boolean removeBook(String bookId) {
        if (books.remove(bookId) != null) {
            saveBooksToFile();
            userServiceShow.showMessage("Book removed successfully.");
            return true;
        } else {
            userServiceShow.showMessage("Book not found.");
            return false;
        }
    }

    // Get a book by ID
    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    // Get all books in a category
    public Map<String, Book> getBooksByCategory(String categoryName) {
        Category category = categories.get(categoryName);
        if (category != null) {
            return category.getBooks().stream()
                    .collect(Collectors.toMap(book -> String.valueOf(book.getProductId()), book -> book));
        }
        return Collections.emptyMap();
    }

    // Save all books to a file
    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE_PATH))) {
            writer.write("BookId,Name,Type,Price,Quantity,Description\n");
            for (Book book : books.values()) {
                writer.write(String.format("%d,%s,%s,%n",
                        book.getProductId(),
                        book.getName(),
                        book.getType()
                        ));
            }
        } catch (IOException e) {
            userServiceShow.showMessage("Error writing books to file: " + e.getMessage());
        }
    }

    // Load all books from a file
    private Map<String, Book> loadBooksFromFile() {
        Map<String, Book> loadedBooks = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE_PATH))) {
            reader.readLine(); // Skip header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (split.length != 6) {
                    System.err.println("Invalid data format: " + line);
                    continue;
                }

                try {
                    int bookId = Integer.parseInt(split[0].trim());
                    String name = split[1].trim();
                    String type = split[2].trim();
                    double price = Double.parseDouble(split[3].trim());
                    int quantity = Integer.parseInt(split[4].trim());
                    String description = split[5].trim();

                    Book book = new Book(bookId, name, type, price, quantity, description);
                    loadedBooks.put(String.valueOf(bookId), book);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid data format in file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Book file not found: " + BOOK_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error reading books from file: " + e.getMessage());
        }
        return loadedBooks;
    }

    public int getBookById(int productId) {
    return productId;
    }
}
