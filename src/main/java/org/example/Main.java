package org.example;

import org.example.adapter.HashOrderSourceAdapter;
import org.example.adapter.PipeOrderSourceAdapter;
import org.example.model.Order;
import org.example.report.FileReportPrinter;
import org.example.report.IReportPrinter;
import org.example.service.IDiscountPolicy;
import org.example.service.IOrderService;
import org.example.service.OrderService;
import org.example.service.StepDiscountPolicy;
import org.example.source.orderSource.IOrderSource;
import org.example.source.rawDataSource.FileRawDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() {
        List<IOrderSource> sources = List.of(
                new PipeOrderSourceAdapter(new FileRawDataSource("discount_day.txt")),
                new HashOrderSourceAdapter(new FileRawDataSource("discount_day_without_ext"))
        );

        List<Order> allOrders = new ArrayList<>();
        for (IOrderSource source : sources) {
            allOrders.addAll(source.getOrders());
        }

        IDiscountPolicy discountPolicy = new StepDiscountPolicy(0.50, 0.05);
        IOrderService orderService = new OrderService(10.0, discountPolicy);

        Map<String, Double> totals = orderService.proccesOrders(allOrders);

        IReportPrinter reportPrinter = new FileReportPrinter("result.txt");
        reportPrinter.printReport(totals);
    }
}
