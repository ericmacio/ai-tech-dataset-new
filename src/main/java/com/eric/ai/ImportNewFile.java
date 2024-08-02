package com.eric.ai;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryDto;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.dto.ItemDto;
import com.eric.ai.dto.JsonFileDtoMapper;
import com.eric.ai.service.CategoryBuilder;
import com.eric.ai.service.CategoryDtoBuilder;

import java.util.List;


public class ImportNewFile {

    static final String NAME = "compute";
    static final String ORIG_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/1_compute.json";
    static final String INPUT_FILENAME = "src/main/resources/ai-tech-dataset-files/new/1_compute.ndjson";
    static final String OUTPUT_FILENAME = "src/main/resources/ai-tech-dataset-files/new/out_1_compute.json";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        List<ItemDto> itemDtoList = JsonFileDtoMapper.getItemDtoListFromFile(INPUT_FILENAME);
        CategoryDto categoryDto =  CategoryDtoBuilder.categoryDtoFromItemDto(itemDtoList, NAME);
        JsonFileDtoMapper.mergeCategoryDtoFile(categoryDto, ORIG_FILENAME, OUTPUT_FILENAME);
        CategoryFileDto categoryFileDto = new CategoryFileDto(NAME, OUTPUT_FILENAME, CHILD_FOLDER);
        Category category = CategoryBuilder.recursiveCategoryBuilder(categoryFileDto, null);
        category.recursiveDisplay();

    }
}
