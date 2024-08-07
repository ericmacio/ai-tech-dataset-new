package com.eric.ai;

import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.ItemService;

public class FindInstance {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        final String KEYWORD = "chatbot";

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        ItemService.getItemStream(mastersRecordDto, FILE_FOLDER, CHILD_FOLDER)
                .filter(item -> item.instancesContainsWordIgnoreCase(KEYWORD))
                .forEach(System.out::println);

    }

}
