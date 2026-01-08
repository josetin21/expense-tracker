package com.josetin.expense_tracker.dto.response;

import java.math.BigDecimal;

public class IncomeExpenseSummaryResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;

    public IncomeExpenseSummaryResponse(
            BigDecimal totalIncome,
            BigDecimal totalExpense,
            BigDecimal balance
    ){
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
