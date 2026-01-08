package com.josetin.expense_tracker.controller;

import com.josetin.expense_tracker.dto.request.CreateTransactionRequest;
import com.josetin.expense_tracker.dto.request.UpdateTranactionRequest;
import com.josetin.expense_tracker.dto.response.CategoryWiseExpense;
import com.josetin.expense_tracker.dto.response.IncomeExpenseSummaryResponse;
import com.josetin.expense_tracker.dto.response.MonthlyExpenseResponse;
import com.josetin.expense_tracker.dto.response.TransactionResponse;
import com.josetin.expense_tracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    //POST
    @PostMapping()
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody CreateTransactionRequest createTransactionRequest){
        return ResponseEntity.ok(transactionService.createTransaction(createTransactionRequest));
    }

    //GET
    @GetMapping()
    public List<TransactionResponse> getAllTransaction(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<TransactionResponse>> getTransactionByUser(@PathVariable Long userId, Pageable pageable){
        return ResponseEntity.ok(transactionService.getTransactionByUser(userId, pageable));
    }

    @GetMapping("/user/{userId}/total-expense")
    public ResponseEntity<BigDecimal> getTotalExpense(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getTotalExpenseByUser(userId));
    }

    @GetMapping("/user/{userId}/category-wise")
    public ResponseEntity<CategoryWiseExpense> getCategoryWiseExpense(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getCategoryWiseExpense(userId));
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<IncomeExpenseSummaryResponse> getIncomeExpenseSummary(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getIncomeExpenseSummary(userId));
    }

    @GetMapping("/user/{userId}/monthly-expense")
    public ResponseEntity<MonthlyExpenseResponse> getMonthlyExpense(@PathVariable Long userId){
        return ResponseEntity.ok(transactionService.getMonthlyExpense(userId));
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id,
                                                                 @Valid @RequestBody UpdateTranactionRequest request){
        return ResponseEntity.ok(transactionService.updateTransaction(id, request));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return  ResponseEntity.noContent().build();
    }


}
