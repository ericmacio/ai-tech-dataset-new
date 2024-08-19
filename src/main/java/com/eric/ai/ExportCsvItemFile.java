package com.eric.ai;

import com.eric.ai.dto.JsonCategoryDto;
import com.eric.ai.exceptions.CsvFileException;
import com.eric.ai.repository.JsonFileRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ExportCsvItemFile {

    static final String OUTPUT_CSV_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref-export.csv";
    static final String JSON_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref.json";

    public static void main(String[] args) {

        final String SEPARATOR = ";";

        List<String> headers = Arrays.asList("category", "domain", "description", "provider", "product");

        try (BufferedWriter writer = Files.newBufferedWriter(
                Path.of(OUTPUT_CSV_FILENAME), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            try {
                String header = String.join(SEPARATOR, headers);
                writer.write(header);
                writer.newLine();
            } catch (IOException e) {
                throw new CsvFileException("Cannot write headers to file: " + OUTPUT_CSV_FILENAME + ". Exception: " + e.getMessage());
            }
            List<JsonCategoryDto> jsonCategoryDtoList = JsonFileRepository.getJsonCategoryDtoListFromFile(JSON_FILENAME);
            jsonCategoryDtoList.stream()
                    .flatMap(jsonCategoryDto -> jsonCategoryDto.getDataStream(SEPARATOR))
                    .forEach(str -> {
                        try {
                            writer.write(str);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new CsvFileException("Cannot write to file: " + OUTPUT_CSV_FILENAME + ". Exception: " + e.getMessage());
                        }
                    });
            writer.flush();
        } catch (IOException e) {
            throw new CsvFileException("Cannot create writer for file: " + OUTPUT_CSV_FILENAME + ". Exception: " + e.getMessage());
        }

    }

}
