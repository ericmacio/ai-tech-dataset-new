package com.eric.ai;

import com.eric.ai.domain.Item;
import com.eric.ai.repository.JsonFileRepository;
import com.eric.ai.dto.MastersRecordDto;
import com.eric.ai.service.CategoryService;
import com.eric.ai.service.ItemService;

import java.util.*;

public class FindAny {

    static final String MASTERS_RECORD_FILENAME = "src/main/resources/ai-tech-dataset-files/orig/0_masters-record.json";
    static final String FILE_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";
    static final String CHILD_FOLDER = "src/main/resources/ai-tech-dataset-files/orig/";

    public static void main(String[] args) {

        final String KEY_WORD = "polly";

        List<Item> itemList = new ArrayList<>();

        MastersRecordDto mastersRecordDto = JsonFileRepository.getMastersRecordDto(MASTERS_RECORD_FILENAME);

        CategoryService.getCategoryStream(mastersRecordDto, FILE_FOLDER , CHILD_FOLDER)
                .flatMap(category -> category.getDataStream("/"))
                .filter(str -> str.toLowerCase().contains(KEY_WORD.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("------------------------");

        CategoryService.getCategoryStream(mastersRecordDto, FILE_FOLDER, CHILD_FOLDER)
                .filter(category -> category.containsWord(KEY_WORD))
                .forEach(cat -> itemList.addAll(cat.getItems()));

        ItemService.getItemStream(mastersRecordDto, FILE_FOLDER, CHILD_FOLDER)
                .filter(item -> ItemService.itemContainsWord(item, KEY_WORD))
                .forEach(itemList::add);

        Set<Item> itemSetList = new HashSet<>(itemList);
        itemSetList.forEach(System.out::println);
    }

}
