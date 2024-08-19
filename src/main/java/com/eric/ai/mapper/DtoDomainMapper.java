package com.eric.ai.mapper;

import com.eric.ai.dto.CsvItemDto;
import com.eric.ai.dto.JsonItemDto;

public class DtoDomainMapper {

    public static JsonItemDto csvItemDtoToJsonItemDto(CsvItemDto dto) {
        return new JsonItemDto(dto.description(), dto.provider(), dto.product());
    }
}
