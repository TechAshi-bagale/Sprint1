package com.entity;

import java.time.LocalDate;
import javax.persistence.*;

/**
 * Entity representing a payment made by a customer for an order.
 */
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    // Default constructor
    public Payment() {}

    // Constructor for new payment creation
    public Payment(double amount, LocalDate date, String method, Order order) {
        this.amount = amount;
        this.paymentDate = date;
        this.paymentMethod = method;
        this.order = order;
        this.customer = order.getCustomer(); // Get customer from order
    }

    // Full constructor
    public Payment(Customer customer, Order order, LocalDate paymentDate, double amount, String paymentMethod) {
        this.customer = customer;
        this.order = order;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    @Override
    public String toString() {
        return "💵 Payment Details\n" +
               "---------------------\n" +
               "ID: " + id +
               "\nCustomer Name: " + (customer != null ? customer.getFullName() : "N/A") +
               "\nOrder ID: " + (order != null ? order.getId() : "N/A") +
               "\nPayment Date: " + paymentDate +
               "\nAmount: ₹" + amount +
               "\nPayment Method: " + paymentMethod + "\n";
    }
}
