package com.eric.ai;

import com.eric.ai.dto.JsonCategoryDto;
import com.eric.ai.repository.JsonFileRepository;

import java.util.List;

public class FindAnyJson {

    static final String JSON_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref.json";

    public static void main(String[] args) {

        final String WORD = "graph";

        System.out.println("Find occurrences for word: " + WORD);
        System.out.println("----------------------------------------");
        List<JsonCategoryDto> jsonCategoryDtoList = JsonFileRepository.getJsonCategoryDtoListFromFile(JSON_FILENAME);
        jsonCategoryDtoList.stream()
                .flatMap(jsonCategoryDto -> jsonCategoryDto.getDataStream(";"))
                .filter(str -> str.contains(WORD))
                .forEach(System.out::println);

    }

}
