package com.revature.scoops.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    private String username;
    private String fName;
    private String lName;
    private String password;
    private int balance;
    private int is_Admin;

    public Customer(String username, String fName, String lName, String password, int balance, int isAdmin){
        super();
        this.username =  username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = balance;
        this.is_Admin = isAdmin;
    }

    public Customer(){

    }

    public int getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIsAdmin(int isAdmin) {
        this.is_Admin = isAdmin;
    }

    public int getIsAdmin() {
        return is_Admin;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "Username: " + username + '\'' +
                "First Name: " + fName + '\'' +
                        "| Last Name: " + lName + '\'' +
                        "| Balance: " + balance + '\'' +
                        "| isAdmin: " + is_Admin + '\'' +
                        "| Password: " + password + '\'';
    }

}
