package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Expense;
import org.yascode.persistence.repository.ExpenseRepository;
import org.yascode.service.business.ExpenseService;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * @return all expenses
     */
    @Override
    public List<Expense> allExpenses() {
        return expenseRepository.findAll();
    }
}
