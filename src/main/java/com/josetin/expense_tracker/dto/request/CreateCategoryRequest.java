package com.josetin.expense_tracker.dto.request;

import jakarta.validation.constraints.NotBlank;


public class CreateCategoryRequest {

    @NotBlank(message = "Category name is Required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
