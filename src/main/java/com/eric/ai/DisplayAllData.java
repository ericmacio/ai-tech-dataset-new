package com.eric.ai;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.CategoryService;


public class DisplayAllData {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        CategoryService.getCategoryStream(mastersRecordDto, FILE_FOLDER , CHILD_FOLDER)
                .forEach(Category::recursiveDisplay);

    }
}