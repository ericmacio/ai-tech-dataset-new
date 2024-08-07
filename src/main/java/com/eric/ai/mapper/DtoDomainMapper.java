package com.eric.ai.mapper;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryDto;

public class DtoDomainMapper {

    public static Category dtoToCategoryBuilder (CategoryDto dto) {

        return new Category(dto.name(), dto.parents(), dto.acronym(), dto.level(), dto.childNames(), dto.items());
    }
}
