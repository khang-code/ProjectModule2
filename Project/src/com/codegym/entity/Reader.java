package com.codegym.entity;

public class Reader extends User{
    private boolean isBorrow;
    public Reader(int id, String name, String address, String email,boolean isBorrow, Object role) {
        super(id, name, address, email, role);
        this.isBorrow = isBorrow;
    }
}
