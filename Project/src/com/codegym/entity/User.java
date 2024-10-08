package com.codegym.entity;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    public User(String id, String userName, String password, String email) {
        this.name = userName;
        this.email = this.email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {return password;}
}