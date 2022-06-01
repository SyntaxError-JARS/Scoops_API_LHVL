package com.revature.scoops.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    private String item_name;
    private int cost;
    private String color;


    public Menu(String item_name, int cost, String color){
        super();
        this.item_name = item_name;
        this.cost = cost;
        this.color =color;
    }
    public Menu(){

    }

    public String getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "item_name: " + item_name + '\'' +
                        "cost: " + cost + '\'' +
                        "| color: " + color + '\'';
    }
}
