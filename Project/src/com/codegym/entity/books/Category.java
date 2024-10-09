package com.codegym.entity.books;
import java.util.ArrayList;
import java.util.List;

public class Category {

    public static final String OLD_CATEGORY = "Old";
    public static final String NEW_CATEGORY = "New";
    private String categoryName;
    private List<Book> books;
    public Category(String categoryName) {
        if (!categoryName.equalsIgnoreCase(OLD_CATEGORY) || !categoryName.equals(NEW_CATEGORY) && !categoryName.equalsIgnoreCase(OLD_CATEGORY)) {
            throw new IllegalArgumentException("Invalid category name: " + categoryName);
        }
        this.categoryName = categoryName;
        this.books = new ArrayList<>();
    }

    public void addBookToCategory(Book book) {
    }

    public List<Book> getBooks() {
        return books;
    }
}
