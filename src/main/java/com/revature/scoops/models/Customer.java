package com.revature.scoops.models;

public class Customer {
    private String username;
    private String fName;
    private String lName;
    private String password;
    private int balance;
    private boolean isAdmin;

    public Customer(String username, String fName, String lName, String password, int balance, boolean isAdmin){
        super();
        this.username =  username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
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

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
                        "| isAdmin: " + isAdmin + '\'' +
                        "| Password: " + password + '\'';
    }

}
