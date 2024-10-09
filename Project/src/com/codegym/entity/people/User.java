package com.codegym.entity.people;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    protected String username;
    protected String password;
    protected String phoneNumber;
    protected String email;
    protected Set<Role> roles = new HashSet<>();
    protected boolean isApproved;

    public User(String s, String username, String password) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isApproved = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> get() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "username='" + getUsername() + '\'' +
                ", phone number:'" + getPhoneNumber() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", Role:'" + getRoles() + '\'';
    }

    public List<Role> getRoles() {
        return (List<Role>) roles;
    }
}
