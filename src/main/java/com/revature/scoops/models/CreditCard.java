package com.revature.scoops.models;

public class CreditCard {

    private String cc_number;
    private String cc_name;
    private int cvv;
    private String exp_date;
    private int zip;
    private String customer_username;
    private int limit;


    public CreditCard(String cc_number, String cc_name, int cvv, String exp_date, int zip, String customer_username, int limit) {
        super();
        this.cc_name = cc_name;
        this.cc_number = cc_number;
        this.cvv =cvv;
        this.exp_date =exp_date;
        this.zip=zip;
        this.customer_username=customer_username;
        this.limit=limit;
    }

    public int getCvv() {
        return cvv;
    }

    public int getLimit() {
        return limit;
    }

    public int getZip() {
        return zip;
    }

    public String getCc_name() {
        return cc_name;
    }

    public String getCc_number() {
        return cc_number;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
