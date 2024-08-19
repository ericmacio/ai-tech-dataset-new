package com.eric.ai.repository;

import com.eric.ai.dto.JsonCategoryDto;
import com.eric.ai.exceptions.FileNotFoundException;
import com.eric.ai.exceptions.JsonFileException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonFileRepository {

    public static List<JsonCategoryDto> getJsonCategoryDtoListFromFile(String fileName) {
        Path path = Paths.get(fileName);
        if(!Files.exists(path)) {
            throw new FileNotFoundException("File does not exist: " + path);
        }
        byte[] jsonData = null;
        try {
            jsonData = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new JsonFileException("Cannot read file content: " + fileName + ". Exception: " + e.getMessage());
        }
        List<JsonCategoryDto> jsonCategoryDtoList;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonCategoryDtoList = objectMapper.readValue(jsonData, new TypeReference<List<JsonCategoryDto>>() {});
        } catch (IOException e) {
            throw new JsonFileException("Cannot map json data to ItemContainerDto.class. Exception: " + e.getMessage());
        }
        return jsonCategoryDtoList;
    }
}
