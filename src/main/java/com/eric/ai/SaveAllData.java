package com.eric.ai;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.exceptions.JsonFileException;
import com.eric.ai.service.CategoryService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SaveAllData {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String SAVE_FILE = "src/main/resources/ai-tech-dataset-files/new/all.json";

    public static void main(String[] args) throws IOException {

        List<Category> categoryList = new ArrayList<>();

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        CategoryService.getCategoryStream(mastersRecordDto, FILE_FOLDER , CHILD_FOLDER)
                .forEach(categoryList::add);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.writeValue(new File(SAVE_FILE), categoryList);
        } catch (IOException e) {
            throw new JsonFileException("Cannot write to file: " + SAVE_FILE + ". Exception: " + e.getMessage());
        }

    }
}
