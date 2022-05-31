package com.revature.scoops.models;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order")
public class Order {
    @Id
    private String itemName;
    private int cost;
    private String color;

    public Order(String itemName, int cost, String color){
        super();
        this.itemName = itemName;
        this.cost = cost;
        this.color = color;
    }
    public Order (){

    }

    public int getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public String getItemName() {
        return itemName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return
                "itemName: " + itemName + '\'' +
                        "cost: " + cost + '\'' +
                        "| color: " + color + '\'';
    }
}
