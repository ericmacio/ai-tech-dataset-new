package com.eric.ai.service;

import com.eric.ai.domain.Category;
import com.eric.ai.dto.CategoryDto;
import com.eric.ai.dto.CategoryFileDto;
import com.eric.ai.dto.JsonFileDtoMapper;
import com.eric.ai.mapper.DtoDomainMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {

    public static Category recursiveCategoryBuilder(CategoryFileDto categoryFileDto, Category parentCategory)  {
        CategoryDto categoryDto = JsonFileDtoMapper.getCategoryDtoFromFile(categoryFileDto.fileName());
        Category category = DtoDomainMapper.dtoToCategoryBuilder(categoryDto);
        if(parentCategory != null) {
            setCategoryParents(parentCategory, category);
        }
        setItemsParents(category);
        category.getChildNames().stream()
                .map(name -> buildChildCategory(categoryFileDto, name, category))
                .forEach(category::addChild);
        return category;
    }

    private static Category buildChildCategory(CategoryFileDto categoryFileDto, String childCategoryName, Category category) {
        String childFileName = categoryFileDto.childFolder() + childCategoryName + ".json";
        CategoryFileDto childCategoryFileDto = new CategoryFileDto(childCategoryName, childFileName, categoryFileDto.childFolder());
        return CategoryBuilder.recursiveCategoryBuilder(childCategoryFileDto, category);
    }

    private static void setCategoryParents(Category parentCategory, Category category) {
        List<String> parents = new ArrayList<>(parentCategory.getParents());
        parents.add(category.getParent());
        category.setParents(parents);
    }

    private static void setItemsParents(Category category) {
        List<String> parents = new ArrayList<>(category.getParents());
        parents.add(category.getName());
        category.getItems().forEach(i -> i.setParents(parents));
    }

}
