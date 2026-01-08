package com.josetin.expense_tracker.dto.response;

import java.math.BigDecimal;
import java.util.Map;

public class MonthlyExpenseResponse {

    private final Map<String, BigDecimal> monthlyExpense;

    public MonthlyExpenseResponse(Map<String, BigDecimal> monthlyExpense){
        this.monthlyExpense = monthlyExpense;
    }

    public Map<String, BigDecimal> getMonthlyExpense() {
        return monthlyExpense;
    }
}
