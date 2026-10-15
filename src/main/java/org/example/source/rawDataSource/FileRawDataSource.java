package org.example.source.rawDataSource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileRawDataSource implements IRawDataSource {

    private final String filePath;

    public FileRawDataSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> getRawData() {
        try {
            return Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла" + filePath, e);
        }
    }
}
