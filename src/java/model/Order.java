/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thinh
 */
public class Order {
    private int id;
    private String dateOrder;
    private double totalprice;
    private User user;
    private int status;

    public Order() {
    }

    public Order(int id, String dateOrder, double totalprice, User user, int status) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.totalprice = totalprice;
        this.user = user;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", dateOrder=" + dateOrder + ", totalprice=" + totalprice + ", user=" + user + ", status=" + status + '}';
    }

    public void setStatus(int status) {
        this.status = status;
    }   
    
}
