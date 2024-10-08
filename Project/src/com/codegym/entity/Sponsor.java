package com.codegym.entity;

public class Sponsor extends Reader{
    boolean letBorrow;
    boolean letReturn;
    public Sponsor(String s, String id, String name, String email) {
        super(s, id, name, email);
    }
}
