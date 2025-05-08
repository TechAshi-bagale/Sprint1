package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity representing an Order placed by a Customer for an Art item.
 */
@Entity
@Table(name = "orders") // 'order' is a reserved SQL keyword
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Many orders can belong to one customer
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Many orders can be placed for one art piece
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "art_id", nullable = false)
    private Art art;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(Customer customer, Art art, LocalDate orderDate, int quantity, double totalPrice) {
        this.customer = customer;
        this.art = art;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Art getArt() {
        return art;
    }

    public void setArt(Art art) {
        this.art = art;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Override
    public String toString() {
        return "\n===== Order Details =====" +
               "\nOrder ID      : " + id +
               "\nCustomer Name : " + (customer != null ? customer.getFullName() : "N/A") +
               "\nArt Title     : " + (art != null ? art.getTitle() : "N/A") +
               "\nArtist Name   : " + (art != null && art.getArtist() != null ? art.getArtist().getArtistName() : "N/A") +
               "\nOrder Date    : " + orderDate +
               "\nQuantity      : " + quantity +
               "\nTotal Price   : ₹" + totalPrice;
    }

}
