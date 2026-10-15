package org.example.report;

import java.util.Map;

public interface IReportPrinter {
    void printReport(Map<String, Double> totals);
}
