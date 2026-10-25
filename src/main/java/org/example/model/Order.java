package org.example.model;

import java.time.LocalDateTime;

public class Order {
    private final LocalDateTime orderTime;
    private final String companyName;
    private final double weight;

    public Order(LocalDateTime orderTime, String companyName, double weight) {

        if (orderTime == null) {
            throw new IllegalArgumentException("Время не может быть пустым");
        }

        if (companyName == null || companyName.isBlank()) {
            throw new IllegalArgumentException("Название компании не может быть пустым");
        }

        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("Вес заказа должен быть строго больше нуля");
        }

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
