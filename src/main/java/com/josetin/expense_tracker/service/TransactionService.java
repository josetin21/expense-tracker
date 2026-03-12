package com.josetin.expense_tracker.service;

import com.josetin.expense_tracker.dto.request.CreateTransactionRequest;
import com.josetin.expense_tracker.dto.request.UpdateTranactionRequest;
import com.josetin.expense_tracker.dto.response.CategoryWiseExpense;
import com.josetin.expense_tracker.dto.response.IncomeExpenseSummaryResponse;
import com.josetin.expense_tracker.dto.response.MonthlyExpenseResponse;
import com.josetin.expense_tracker.dto.response.TransactionResponse;
import com.josetin.expense_tracker.entity.Category;
import com.josetin.expense_tracker.entity.Transaction;
import com.josetin.expense_tracker.entity.TransactionType;
import com.josetin.expense_tracker.entity.User;
import com.josetin.expense_tracker.exception.ResourceNotFoundException;
import com.josetin.expense_tracker.mapper.TransactionAnalyticsMapper;
import com.josetin.expense_tracker.mapper.TransactionMapper;
import com.josetin.expense_tracker.repo.CategoryRepo;
import com.josetin.expense_tracker.repo.TransactionRepo;
import com.josetin.expense_tracker.repo.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GroovyWebApplicationContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    private final TransactionMapper transactionMapper;
    private final TransactionAnalyticsMapper transactionAnalyticsMapper;

    public TransactionService(
            TransactionRepo transactionRepo,
            CategoryRepo categoryRepo,
            UserRepo userRepo,
            TransactionMapper transactionMapper,
            TransactionAnalyticsMapper transactionAnalyticsMapper
    ){
        this.transactionRepo = transactionRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
        this.transactionMapper = transactionMapper;
        this.transactionAnalyticsMapper = transactionAnalyticsMapper;
    }


    public TransactionResponse createTransaction(CreateTransactionRequest request, Long userId){

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        Category category = categoryRepo.findByNameIgnoreCase(request.getCategoryName())
                .orElseGet(()->{
                    Category newCategory = new Category();
                    newCategory.setName(request.getCategoryName());
                    return categoryRepo.save(newCategory);
                });

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setDate(request.getDate());
        transaction.setUser(user);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepo.save(transaction);
        return transactionMapper.toResponse(savedTransaction);
    }

    public void deleteTransaction(Long transactionId, Long userId){
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(()-> new ResourceNotFoundException("Transaction not Found"));

        if(!transaction.getUser().getId().equals(userId)){
            throw new ResourceNotFoundException("Transaction not found");
        }

        transaction.setDeleted(true);
        transactionRepo.save(transaction);
    }



    public Page<TransactionResponse> getTransactionByUser(Long userId, Pageable pageable){
        Page<Transaction> page = transactionRepo.findByUserIdAndDeletedFalse(userId, pageable);

        return page.map(transactionMapper::toResponse);
    }

    public TransactionResponse updateTransaction(Long transactionId,
                                                 UpdateTranactionRequest request,
                                                 Long userId){

        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(()-> new ResourceNotFoundException("Transaction Not Found"));

        if (!transaction.getUser().getId().equals(userId)){
            throw new ResourceNotFoundException("Transaction Not Found");
        }

        if(transaction.isDeleted()){
            throw new ResourceNotFoundException("Transaction is deleted");
        }

        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not Found"));

        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(category);

        Transaction updated = transactionRepo.save(transaction);
        return transactionMapper.toResponse(updated);
    }

    public BigDecimal getTotalExpenseByUser(Long userId){
        return transactionRepo.getTotalExpenseByUser(userId);
    }

    public CategoryWiseExpense getCategoryWiseExpense(Long userId){
       return transactionAnalyticsMapper.toCategoryWise(
               transactionRepo.getCategoryWiseExpense(userId)
       );
    }

    public IncomeExpenseSummaryResponse getIncomeExpenseSummary(Long userId){
        return transactionAnalyticsMapper.toIncomeExpenseSummary(
                transactionRepo.getIncomeExpenseSummary(userId)
        );
    }

    public MonthlyExpenseResponse getMonthlyExpense(Long userId){
        return transactionAnalyticsMapper.toMonthlyMap(
                transactionRepo.getMonthlyExpense(userId)
        );
    }
}
