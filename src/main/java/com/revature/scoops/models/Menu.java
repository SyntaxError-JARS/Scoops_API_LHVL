package com.revature.scoops.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    private int id;
    private String menuItem;
    private String comment;
    private boolean is_favorite;
    private String order_date;
    private String customerUsername;

    public Menu(int id, String menuItem, String comment, boolean is_favorite, String order_date, String customerUsername){
        super();
        this.id =id;
        this.menuItem=menuItem;
        this.comment=comment;
        this.is_favorite=is_favorite;
        this.order_date=order_date;
        this.customerUsername=customerUsername;
    }
    public Menu(){

    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "id: " + id + '\'' +
                        "menuItem: " + menuItem + '\'' +
                        "| is_favorite: " + is_favorite + '\'' +
                        "| order_date: " + order_date + '\'' +
                        "| customerUsername: " + customerUsername + '\'';
    }
}
