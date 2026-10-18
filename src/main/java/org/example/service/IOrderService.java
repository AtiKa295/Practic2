package org.example.service;

import org.example.model.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    Map<String, Double> proccesOrders(List<Order> orders);
}
