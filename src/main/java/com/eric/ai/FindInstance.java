package com.eric.ai;

import com.eric.ai.domain.Category;
import com.eric.ai.domain.Instance;
import com.eric.ai.domain.Item;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.dto.JsonFileDtoMapper;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.CategoryBuilder;

import java.util.Collection;

public class FindInstance {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        final String KEYWORD = "amazon connect";

        MastersRecordDto mastersRecordDto = JsonFileDtoMapper.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        mastersRecordDto.masters().stream()
                .map(m -> new CategoryFileDto(m, FILE_FOLDER + m + ".json", CHILD_FOLDER))
                .map(categoryFileDto -> CategoryBuilder.recursiveCategoryBuilder(categoryFileDto,null))
                .map(Category::getItemsRecursive)
                .flatMap(Collection::stream)
                .filter(item -> item.instancesContainsWordIgnoreCase(KEYWORD))
                .forEach(System.out::println);

    }

}
