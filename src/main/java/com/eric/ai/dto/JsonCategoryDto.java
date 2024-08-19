package com.eric.ai.dto;

import java.util.List;
import java.util.stream.Stream;

public record JsonCategoryDto(
        String name,
        List<JsonDomainDto> domains
) {
    public Stream<String> getDataStream(String separator) {
        return domains.stream()
                .flatMap(jsonDomainDto -> jsonDomainDto.getDataStream(this.name(), separator));
    }
}
