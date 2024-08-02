package com.eric.ai.service;

import com.eric.ai.domain.Instance;
import com.eric.ai.domain.Item;
import com.eric.ai.dto.CategoryDto;
import com.eric.ai.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDtoBuilder {

    static final Integer DEFAULT_LEVEL = 1;
    static final String DEFAULT_PARENT = "master-categories";
    static final List<String> DEFAULT_CHILD_NAMES = new ArrayList<>();

    public static CategoryDto categoryDtoFromItemDto (List<ItemDto> itemDtoList, String categoryName) {
        return categoryDtoFromItemDto(itemDtoList, categoryName, DEFAULT_PARENT,
                null, DEFAULT_LEVEL, DEFAULT_CHILD_NAMES);
    }

    public static CategoryDto categoryDtoFromItemDto (List<ItemDto> itemDtoList, String categoryName, String parent,
                                                 String acronym, Integer level, List<String> childNames) {

        List<Item> itemList = new ArrayList<>();
        itemDtoList.forEach(itemDto -> {
            Instance instance = new Instance("cloud", "aws", itemDto.aws());
            itemList.add(new Item(itemDto.name(), itemDto.acronym(), List.of(instance)));
        });

        return new CategoryDto(categoryName, parent, acronym, level, childNames, itemList);

    }
}
