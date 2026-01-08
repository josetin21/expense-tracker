package com.josetin.expense_tracker.mapper;

import com.josetin.expense_tracker.dto.request.CreateCategoryRequest;
import com.josetin.expense_tracker.dto.response.CategoryResponse;
import com.josetin.expense_tracker.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CreateCategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        return category;
    }

    public static CategoryResponse toResponse(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
}
