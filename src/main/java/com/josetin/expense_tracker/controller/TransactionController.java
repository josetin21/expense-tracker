package com.josetin.expense_tracker.controller;

import com.josetin.expense_tracker.dto.request.CreateTransactionRequest;
import com.josetin.expense_tracker.dto.request.UpdateTranactionRequest;
import com.josetin.expense_tracker.dto.response.CategoryWiseExpense;
import com.josetin.expense_tracker.dto.response.IncomeExpenseSummaryResponse;
import com.josetin.expense_tracker.dto.response.MonthlyExpenseResponse;
import com.josetin.expense_tracker.dto.response.TransactionResponse;
import com.josetin.expense_tracker.entity.User;
import com.josetin.expense_tracker.service.TransactionService;
import com.josetin.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(TransactionService transactionService, UserService userService){
        this.transactionService = transactionService;
        this.userService = userService;
    }

    private Long getAuthenticatedUserId(Principal principal){
        User user = userService.getAuthenticatedUser(principal.getName());
        return user.getId();
    }

    //POST
    @PostMapping()
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody CreateTransactionRequest request,
            Principal principal){

        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.createTransaction(request, userId));
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> getMyTransaction(
            Pageable pageable,
            Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.getTransactionByUser(userId, pageable));
    }

    @GetMapping("/total-expense")
    public ResponseEntity<BigDecimal> getTotalExpense(Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.getTotalExpenseByUser(userId));
    }

    @GetMapping("/category-wise")
    public ResponseEntity<CategoryWiseExpense> getCategoryWiseExpense(Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.getCategoryWiseExpense(userId));
    }

    @GetMapping("/summary")
    public ResponseEntity<IncomeExpenseSummaryResponse> getIncomeExpenseSummary(Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.getIncomeExpenseSummary(userId));
    }

    @GetMapping("/monthly-expense")
    public ResponseEntity<MonthlyExpenseResponse> getMonthlyExpense(Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.getMonthlyExpense(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTranactionRequest request,
            Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        return ResponseEntity.ok(transactionService.updateTransaction(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Long id,
            Principal principal){
        Long userId = getAuthenticatedUserId(principal);
        transactionService.deleteTransaction(id, userId);
        return ResponseEntity.noContent().build();
    }
}
