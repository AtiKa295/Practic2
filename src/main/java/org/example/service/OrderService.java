package org.example.service;

import org.example.model.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrderService implements IOrderService{

    private final double basePricePerKg;
    private final IDiscountPolicy discountePolicy;

    public OrderService(double basePricePerKg, IDiscountPolicy discountePolicy) {
        this.basePricePerKg = basePricePerKg;
        this.discountePolicy = discountePolicy;
    }

    @Override
    public Map<String, Double> proccesOrders(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return Map.of();
        }

        List<Order> sortedOrders = new ArrayList<>(orders);
        sortedOrders.sort(Comparator.comparing(Order::getOrderTime));

        Map<String, Double> companyTotals = new LinkedHashMap<>();


        for (int i = 0; i < sortedOrders.size(); i++) {
            Order order = sortedOrders.get(i);

            double discount = discountePolicy.calulateDiscount(i);
            double effectivePrice = basePricePerKg * (1.0 - discount);
            double orderCost = order.getWeight() * effectivePrice;

            companyTotals.merge(order.getCompanyName(), orderCost, Double::sum);
        }

        return companyTotals;
    }
}
