package com.codegym.entity;

public class Sponsor extends Reader{
    private boolean letProvide;
    private boolean everProvide;
    public Sponsor(int id, String name, String address, String email, boolean isBorrow, boolean letProvide, boolean everProvide, Object role) {
        super(id, name, address, email, isBorrow, role);
        this.letProvide = letProvide;
        this.everProvide = everProvide;
    }
}
