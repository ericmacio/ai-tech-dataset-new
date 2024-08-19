package com.eric.ai;

import com.eric.ai.dto.*;
import com.eric.ai.exceptions.CsvFileException;
import com.eric.ai.exceptions.JsonFileException;
import com.eric.ai.mapper.DtoDomainMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class ImportCsvItemFile {

    static final String CSV_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref.csv";
    static final String OUTPUT_JSON_FILENAME = "src/main/resources/ai-tech-dataset-files/all/data-ref.json";

    public static void main(String[] args) {

        SortedMap<String, SortedMap<String, List<JsonItemDto>>> jsonMap = new TreeMap<>();

        getCsvItemDtoList(CSV_FILENAME).forEach(csvItemDto -> {
            String category = csvItemDto.category();
            if(!jsonMap.containsKey(category)) {
                jsonMap.put(category, new TreeMap<>());
            }
            String domain = csvItemDto.domain();
            if(!jsonMap.get(category).containsKey(domain)) {
                jsonMap.get(category).put(domain, new ArrayList<>());
            }
            jsonMap.get(category).get(domain).add(DtoDomainMapper.csvItemDtoToJsonItemDto(csvItemDto));
        });

        List<JsonCategoryDto> jsonCategoryDtoList = new ArrayList<>();

        jsonMap.forEach((category, domainMap) -> {
            List<JsonDomainDto> jsonDomainDtoList = new ArrayList<>();
            domainMap.forEach((domain, jsonItemList) -> {
                JsonDomainDto jsonDomainDto = new JsonDomainDto(domain, jsonItemList);
                jsonDomainDtoList.add(jsonDomainDto);
            });
            jsonCategoryDtoList.add(new JsonCategoryDto(category, jsonDomainDtoList));
        });

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.writeValue(new File(OUTPUT_JSON_FILENAME), jsonCategoryDtoList);
        } catch (IOException e) {
            throw new JsonFileException("Cannot write to file: " + OUTPUT_JSON_FILENAME + ". Exception: " + e.getMessage());
        }

    }

    private static List<CsvItemDto> getCsvItemDtoList(String fileName) {
        List<CsvItemDto> csvItemDtoList = new ArrayList<>();
        try {
            Files.lines(Path.of(fileName))
                    .map(line -> line.split(";"))
                    .map(createCsvDto())
                    .forEach(csvItemDtoList::add);

        } catch (IOException e) {
            throw new CsvFileException("Can not read file: " + fileName + ". Exception: " + e.getMessage());
        }
        return csvItemDtoList;
    }

    private static Function<String[], CsvItemDto> createCsvDto() {
        return strArray -> new CsvItemDto(strArray[0], strArray[1], strArray[2], strArray[3], strArray[4]);
    }

}
