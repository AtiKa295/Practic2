package org.example.model;

import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime orderTime;
    private final String companyName;
    private final double weight;

    public Order(LocalDateTime orderTime, String companyName, double weight) {
        this.orderTime = orderTime;
        this.companyName = companyName;
        this.weight = weight;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getWeight() {
        return weight;
    }
}
