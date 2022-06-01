package com.revature.scoops.models;

import javax.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    private int id;
    private String menu_item;
    private String comment;
    private int is_favorite;
    private String order_date;
    private String customer_username;

    public Orders(int id, String menu_item, String comment, int is_favorite, String order_date, String customer_username){
        super();
        this.id=id;
        this.menu_item=menu_item;
        this.comment=comment;
        this.is_favorite=is_favorite;
        this.order_date=order_date;
        this.customer_username=customer_username;
    }
    public Orders(){

    }

    public String getOrder_date() {
        return order_date;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public int getIs_favorite() {
        return is_favorite;
    }

    public String getCustomer_name() {
        return customer_username;
    }

    public String getMenu_item() {
        return menu_item;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setIs_favorite(int is_favorite) {
        this.is_favorite = is_favorite;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_username = customer_name;
    }

    public void setMenu_item(String menu_item) {
        this.menu_item = menu_item;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "id: " + id + '\'' +
                        "menu_item: " + menu_item + '\'' +
                        "comment: " + comment + '\'' +
                        "is_favorite: " + is_favorite + '\'' +
                        "order_date: " + order_date + '\'' +
                        "customer_username: " + customer_username + '\'';
    }
}
