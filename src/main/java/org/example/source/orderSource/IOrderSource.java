package org.example.source.orderSource;

import org.example.model.Order;

import java.util.List;

public interface IOrderSource {
    List<Order> getOrders();
}
