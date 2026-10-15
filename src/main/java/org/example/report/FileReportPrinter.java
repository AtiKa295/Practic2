package org.example.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public class FileReportPrinter implements IReportPrinter{
    private final String outputPath;

    public FileReportPrinter(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void printReport(Map<String, Double> totals) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, StandardCharsets.UTF_8))) {
            for (Map.Entry<String, Double> entry : totals.entrySet()) {
                writer.write(String.format(Locale.US, "%s - %.2f", entry.getKey(), entry.getValue()));
                writer.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка записи отчета в файл" + outputPath, e);
        }
    }
}
