package com.revature.scoops.models;

import javax.persistence.*;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

    @Id
    private String cc_number;
    private String cc_name;
    private int ccv;
    private String exp_date;
    private int zip;
    private int limit;
    //@OneToMany
    //@JoinColumn(name = "Costomer", referencedColumnName = "username")

    private String customer_username;


    public CreditCard(String cc_number, String cc_name, int ccv, String exp_date, int zip, String customer_username, int limit) {
        super();
        this.cc_name = cc_name;
        this.cc_number = cc_number;
        this.ccv =ccv;
        this.exp_date =exp_date;
        this.zip=zip;
        this.customer_username=customer_username;
        this.limit=limit;
    }
    public CreditCard(){

    }

    public int getCvv() {
        return ccv;
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
        this.ccv = cvv;
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
