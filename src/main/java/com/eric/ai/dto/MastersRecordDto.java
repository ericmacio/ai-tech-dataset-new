package com.eric.ai.dto;

import java.util.List;

public record MastersRecordDto(
        String name,
        Integer level,
        List<String> masters,
        List<String> full) {
}
