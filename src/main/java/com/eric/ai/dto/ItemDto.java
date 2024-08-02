package com.eric.ai.dto;

import java.util.List;

public record ItemDto(
        String name,
        String acronym,
        List<String> aws) {}
