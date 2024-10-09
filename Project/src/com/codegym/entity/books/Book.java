package com.codegym.entity.books;

import java.util.concurrent.atomic.AtomicInteger;

public class Book implements Product {
    private static AtomicInteger idCounter = new AtomicInteger();  // Fixed typo
    private int bookId;
    private String title;
    private String author;

    // Constructor
    public Book(int productId, String title, String author, double price, int quantity, String description) {
        this.bookId = idCounter.incrementAndGet();  // Increment book ID
        this.title = title;
        this.author = author;

        // Ensure the static idCounter stays updated in case bookId is manually set higher
        if (bookId > idCounter.get()) {
            idCounter.set(bookId);
        }
    }

    // Implement methods from the Product interface
    @Override
    public String getName() {
        return this.title;
    }

    @Override
    public void setName(String name) {
        this.title = name;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int getBookID() {
        return this.bookId;
    }

    // Getters and setters
    public static AtomicInteger getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(AtomicInteger idCounter) {
        Book.idCounter = idCounter;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("Books{Id: %d | Book's title: '%s' | Book's author: '%s'}",
                bookId, title, author);
    }

    public int getProductId() {
        return bookId;
    }

    public Object getType() {
        return "Book";
    }

    public void setType(String type) {
    }
}