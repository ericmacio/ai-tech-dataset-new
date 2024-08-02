package com.eric.ai.dto;

import com.eric.ai.domain.Item;

import java.util.List;

public record CategoryDto(
        String name,
        String parent,
        String acronym,
        Integer level,
        List<String>childNames,
        List<Item> items) {
}
