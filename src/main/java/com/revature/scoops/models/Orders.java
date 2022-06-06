package com.revature.scoops.models;

import javax.persistence.*;

@Entity

@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String menu_item;
    private int scoopNum;
    private int totalCost;
    private String order_date;
    private String customer_username;
    public Orders(int id, String menu_item, int scoopNum, int totalCost, String order_date, String customer_username){
        super();
        this.id =id;
        this.menu_item=menu_item;
        this.scoopNum=scoopNum;
        this.totalCost=totalCost;
        this.order_date=order_date;
        this.customer_username=customer_username;
    }
    public Orders(){

    }

    public String getOrder_date() {
        return order_date;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getId() {
        return id;
    }

    public int getScoopNum() {
        return scoopNum;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public void setScoopNum(int scoopNum) {
        this.scoopNum = scoopNum;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "id: " + id + '\'' +
                        "menu_item: " + menu_item + '\'' +
                        "scoopNum: " + scoopNum + '\'' +
                        "totalCost: " + totalCost + '\'' +
                        "order_date: " + order_date + '\'' +
                        "customer_username: " + customer_username + '\'';
    }
}
