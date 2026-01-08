package com.josetin.expense_tracker.dto.response;

import java.math.BigDecimal;
import java.util.Map;

public class CategoryWiseExpense {

    private final Map<String, BigDecimal> categories;

    public CategoryWiseExpense(Map<String, BigDecimal> categories){
        this.categories = categories;
    }

    public Map<String, BigDecimal> getCategories(){
        return categories;
    }
}
