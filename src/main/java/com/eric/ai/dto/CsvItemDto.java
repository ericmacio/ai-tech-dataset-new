package com.eric.ai.dto;

public record CsvItemDto(
        String category,
        String domain,
        String description,
        String provider,
        String product
) {
}
