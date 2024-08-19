package com.eric.ai;

import com.eric.ai.dto.JsonCategoryDto;
import com.eric.ai.repository.JsonFileRepository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GetDomains {

    static final String JSON_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref.json";

    public static void main(String[] args) {

        final String SEPARATOR = ";";
        Set<String> domainSet = new TreeSet<>();
        final String KEY_WORD = "customer";

        List<JsonCategoryDto> jsonCategoryDtoList = JsonFileRepository.getJsonCategoryDtoListFromFile(JSON_FILENAME);
        jsonCategoryDtoList.stream()
                .flatMap(jsonCategoryDto -> jsonCategoryDto.getDataStream(SEPARATOR))
                .map(str -> str.split(SEPARATOR))
                .map(strArray -> strArray[1])
                .filter(str -> str.contains(KEY_WORD))
                .forEach(domainSet::add);

        domainSet.forEach(System.out::println);

    }

}
