/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Handez
 */
public class HistoryOrder implements Serializable{
    private String orderid;
    private String product;
    private double price;
    private int quantity; 
    private String date;
    private String photo;

    public HistoryOrder(String orderid, String product, double price, int quantity, String date, String photo) {
        this.orderid = orderid;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
        this.photo = photo;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    

    public String getProduct() {
        return product;
    }

    public void setProduct(String Product) {
        this.product = Product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public HistoryOrder() {
    }

   

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    

    
    
}
