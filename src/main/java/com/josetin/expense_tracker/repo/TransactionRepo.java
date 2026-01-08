package com.josetin.expense_tracker.repo;

import com.josetin.expense_tracker.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserIdAndDeletedFalse (Long userId, Pageable pageable);

    @Query("""
        SELECT COALESCE(SUM(t.amount),0)
        FROM Transaction t
        WHERE t.user.id = :userId
            AND t.type = 'EXPENSE'
            AND t.deleted = false
    """)
    BigDecimal getTotalExpenseByUser (Long userId);


    @Query("""
        SELECT c.name, COALESCE(SUM(t.amount),0)
        FROM Transaction t JOIN t.category c
        WHERE t.user.id = :userId
            AND t.type = 'EXPENSE'
            AND t.deleted = false
        GROUP BY c.name
    """)
    List<Object[]> getCategoryWiseExpense(Long userId);

    @Query("""
        SELECT t.type, COALESCE(SUM (t.amount),0)
        FROM Transaction t
        WHERE t.user.id = :userId
           AND t.deleted = false
        GROUP BY t.type
   """)
    List<Object[]> getIncomeExpenseSummary(Long userId);


    @Query("""
        SELECT FUNCTION('to_char', t.date, 'YYYY-MM'), COALESCE(SUM (t.amount),0)
        FROM Transaction t
        WHERE t.user.id = :userId
            AND t.type = 'EXPENSE'
            AND t.deleted = false
        GROUP BY FUNCTION('to_char', t.date, 'YYYY-MM')
        ORDER BY FUNCTION('to_char', t.date, 'YYYY-MM') 
    """)
    List<Object[]> getMonthlyExpense(Long userId);

}
