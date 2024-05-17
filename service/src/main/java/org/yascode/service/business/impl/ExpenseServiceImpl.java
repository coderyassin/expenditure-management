package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Expense;
import org.yascode.persistence.repository.ExpenseRepository;
import org.yascode.persistence.repository.specification.ExpenseSpec;
import org.yascode.service.business.ExpenseService;
import org.yascode.shared.dto.ExpenseDto;
import org.yascode.shared.mapper.ExpenseMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapping<Expense> expenseDtoToExpense;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ExpenseMapping<Expense> expenseDtoToExpense) {
        this.expenseRepository = expenseRepository;
        this.expenseDtoToExpense = expenseDtoToExpense;
    }

    /**
     * @return all expenses
     */
    @Override
    public List<ExpenseDto> allExpenses() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseDtoToExpense::toDto)
                .toList();
    }

    /**
     * @param idUser
     * @param startDate
     * @param endDate
     * @return List of expenses for a user between two dates
     */
    @Override
    public List<ExpenseDto> expenseBetween(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate) {
        return expenseRepository.findAll(ExpenseSpec.expenseBetween(idUser, startDate.map(LocalDate::parse), endDate.map(LocalDate::parse)))
                .stream()
                .map(expenseDtoToExpense::toDto)
                .toList();
    }
}
