package com.eric.ai.dto;

import java.util.List;
import java.util.stream.Stream;

public record JsonDomainDto(
        String name,
        List<JsonItemDto> items
) {
    public Stream<String> getDataStream(String category, String separator) {
        return items.stream()
                .map(item -> item.getData(category + separator + this.name, separator));
    }
}
