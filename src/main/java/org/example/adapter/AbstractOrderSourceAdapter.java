package org.example.adapter;

import org.example.model.Order;
import org.example.source.orderSource.IOrderSource;
import org.example.source.rawDataSource.IRawDataSource;

import java.util.List;

public abstract class AbstractOrderSourceAdapter implements IOrderSource {

    private final IRawDataSource rawDataSource;

    public AbstractOrderSourceAdapter(IRawDataSource rawDataSource) {
        this.rawDataSource = rawDataSource;
    }

    @Override
    public List<Order> getOrders() {
        List<String> rawLines = rawDataSource.getRawData();
        if (rawLines == null) {
            return List.of();
        }

        return rawLines.stream()
                .filter(line -> line != null && !line.isBlank())
                .map(String::trim)
                .map(this::parseLine)
                .toList();
    }

    protected abstract Order parseLine(String line);
}
