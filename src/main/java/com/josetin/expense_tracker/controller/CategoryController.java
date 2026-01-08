package com.josetin.expense_tracker.controller;

import com.josetin.expense_tracker.dto.request.CreateCategoryRequest;
import com.josetin.expense_tracker.dto.response.CategoryResponse;
import com.josetin.expense_tracker.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequest));
    }

    @GetMapping()
    public List<CategoryResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }

    
}
