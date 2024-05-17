package org.yascode.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.yascode.persistence.entity.Expense;
import org.yascode.persistence.repository.projection.ExpenseProjection;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    @Query(value = "select sum(e.amount)  from Expense e where e.user.id = ?1")
    ExpenseProjection findExpenseByUserId(Long userId);

}