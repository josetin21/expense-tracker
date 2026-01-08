package com.josetin.expense_tracker.mapper;

import com.josetin.expense_tracker.dto.response.TransactionResponse;
import com.josetin.expense_tracker.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class  TransactionMapper {

    public TransactionResponse toResponse(Transaction transaction){
        TransactionResponse transactionResponse = new TransactionResponse();

        transactionResponse.setId(transaction.getId());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setType(transaction.getType());
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setCategory_name(transaction.getCategory().getName());

        return transactionResponse;
    }
}
