package com.eric.ai;

import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.exceptions.CsvFileException;
import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.service.CategoryService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class BuildCsv {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CSV_FILE = "src/main/resources/ai-tech-dataset-files/new/all.csv";

    public static void main(String[] args) {

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(CSV_FILE), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
             CategoryService.getCategoryStream(mastersRecordDto, FILE_FOLDER , CHILD_FOLDER)
                .flatMap(category -> category.getDataStream(";"))
                .forEach(str -> {
                    try {
                        writer.write(str);
                        writer.newLine();
                    } catch (IOException e) {
                        throw new CsvFileException("Cannot write to file: " + CSV_FILE + ". Exception: " + e.getMessage());
                    }
                });
             writer.flush();
        } catch (IOException e) {
            throw new CsvFileException("Cannot create writer for file: " + CSV_FILE + ". Exception: " + e.getMessage());
        }

    }
}
