package com.codegym.entity;

public class Reader extends User{
    private boolean isBorrow;


    public Reader(String s, String id, String name, String email) {
        super(s, id, name, email);
        this.isBorrow = isBorrow;
    }
}
