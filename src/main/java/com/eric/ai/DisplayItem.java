package com.eric.ai;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.dto.JsonFileDtoMapper;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.CategoryBuilder;


public class DisplayItem {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        MastersRecordDto mastersRecordDto = JsonFileDtoMapper.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        mastersRecordDto.masters().stream()
                .map(m -> new CategoryFileDto(m, FILE_FOLDER + m + ".json", CHILD_FOLDER))
                .map(categoryFileDto -> CategoryBuilder.recursiveCategoryBuilder(categoryFileDto,null))
                .forEach(Category::recursiveDisplay);

    }
}
