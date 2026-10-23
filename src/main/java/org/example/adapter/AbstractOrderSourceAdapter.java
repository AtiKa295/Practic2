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
                .map(this::safeParseLine)
                .toList();
    }


    private Order safeParseLine(String line) {
        try {
            return parseLine(line);
        } catch (OrderParseException e) {
            throw e;
        } catch (Exception e) {
            throw new OrderParseException("Ошибка получения строки: " + line + " причина: " + e.getMessage(), e);
        }
    }

    protected abstract Order parseLine(String line);
}
