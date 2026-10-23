package org.example.adapter;

import org.example.model.Order;
import org.example.source.rawDataSource.IRawDataSource;

import java.time.LocalDateTime;

public class HashOrderSourceAdapter extends AbstractOrderSourceAdapter{

    public HashOrderSourceAdapter(IRawDataSource rawDataSource) {
        super(rawDataSource);
    }

    @Override
    protected Order parseLine(String line) {
        String[] parts = line.split("#");

        if (parts.length != 3) {
            throw new OrderParseException("Ожидалось 3 поля через #, получено: " + parts.length + " в строке" + line);
        }

        LocalDateTime time = LocalDateTime.parse((parts[0].trim()));
        String company = parts[1].trim();
        double amount = Double.parseDouble(parts[2].trim());

        return new Order(time, company, amount);
    }
}
