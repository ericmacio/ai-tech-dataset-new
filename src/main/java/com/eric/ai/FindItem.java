package com.eric.ai;

import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.ItemService;

public class FindItem {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        final String KEYWORD = "iot";

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        ItemService.getItemStream(mastersRecordDto, FILE_FOLDER, CHILD_FOLDER)
                .filter(i -> i.getName().toLowerCase().contains(KEYWORD.toLowerCase()))
                .forEach(System.out::println);

    }
}
