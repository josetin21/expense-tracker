package com.josetin.expense_tracker.service;

import com.josetin.expense_tracker.dto.request.CreateCategoryRequest;
import com.josetin.expense_tracker.dto.response.CategoryResponse;
import com.josetin.expense_tracker.entity.Category;
import com.josetin.expense_tracker.mapper.CategoryMapper;
import com.josetin.expense_tracker.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    public CategoryService (CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
    }

    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest){
        Category category = CategoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryRepo.save(category);

        return CategoryMapper.toResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategory(){
        return categoryRepo.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toList());
    }
}
