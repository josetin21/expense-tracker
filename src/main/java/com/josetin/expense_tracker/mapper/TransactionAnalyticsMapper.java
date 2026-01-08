package com.josetin.expense_tracker.mapper;

import com.josetin.expense_tracker.dto.response.CategoryWiseExpense;
import com.josetin.expense_tracker.dto.response.IncomeExpenseSummaryResponse;
import com.josetin.expense_tracker.dto.response.MonthlyExpenseResponse;
import com.josetin.expense_tracker.entity.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TransactionAnalyticsMapper {

    public CategoryWiseExpense toCategoryWise(List<Object[]> rows){

        Map<String, BigDecimal> categoryMap = rows
                .stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> BigDecimal.valueOf(((Number)row[1]).doubleValue())
                ));

        return new CategoryWiseExpense(categoryMap);
    }

    public IncomeExpenseSummaryResponse toIncomeExpenseSummary(List<Object[]> rows){

        Map<TransactionType, BigDecimal> totals =
                rows.stream()
                        .collect(Collectors.toMap(
                                row -> (TransactionType) row[0],
                                row -> BigDecimal.valueOf(((Number) row[1]).doubleValue())
                        ));

        BigDecimal income = totals.getOrDefault(TransactionType.INCOME, BigDecimal.ZERO);
        BigDecimal expense = totals.getOrDefault(TransactionType.EXPENSE, BigDecimal.ZERO);

        return new IncomeExpenseSummaryResponse(
                income,
                expense,
                income.subtract(expense)
        );
    }

    public MonthlyExpenseResponse toMonthlyMap(List<Object[]> rows){

        Map<String, BigDecimal> monthlyMap = rows.stream()
                .collect(Collectors.toMap(
                        row -> row[0].toString(),
                        row -> BigDecimal.valueOf(((Number)row[1]).doubleValue())
                ));

        return new MonthlyExpenseResponse(monthlyMap);
    }
}
