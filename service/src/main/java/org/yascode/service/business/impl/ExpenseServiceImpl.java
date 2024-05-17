package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Expense;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.ExpenseRepository;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.persistence.repository.specification.ExpenseSpec;
import org.yascode.service.business.ExpenseService;
import org.yascode.shared.dto.ExpenseDto;
import org.yascode.shared.mapper.ExpenseMapping;
import org.yascode.shared.model.SumOfExpenses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapping<Expense> expenseDtoToExpense;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              UserRepository userRepository,
                              ExpenseMapping<Expense> expenseDtoToExpense) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
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

    /**
     * @param idUser
     * @param startDate
     * @param endDate
     * @return The sum of expenses
     */
    @Override
    public List<SumOfExpenses> sumOfExpenses(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate) {
        Optional<LocalDate> start = startDate.map(LocalDate::parse);
        Optional<LocalDate> end = endDate.map(LocalDate::parse);

        if(idUser.isPresent()) {
            Optional<User> userOptional = userRepository.findById(idUser.get());

            if(userOptional.isPresent()) {
                Double amount = expenseRepository.findAll(ExpenseSpec.expenseBetween(idUser, start, end))
                        .stream()
                        .mapToDouble(Expense::getAmount)
                        .sum();

                User user = userOptional.get();

                SumOfExpenses sumOfExpenses = new SumOfExpenses(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        startDate.isPresent() ? LocalDate.parse(startDate.get()) : null,
                        endDate.isPresent() ? LocalDate.parse(endDate.get()) : null,
                        amount);

                return Collections.singletonList(sumOfExpenses);
            } else {
                return Collections.emptyList();
            }
        } else {
            List<Expense> allExpenses = expenseRepository.findAll(ExpenseSpec.expenseBetween(idUser, start, end));

            List<SumOfExpenses> sumOfExpenses = new ArrayList<>();

            userRepository.findAll().forEach(user -> {
                Double amount = allExpenses.stream()
                        .filter(expense -> expense.getUser().getId().equals(user.getId()))
                        .mapToDouble(Expense::getAmount)
                        .sum();

                SumOfExpenses sumOfExpense = new SumOfExpenses(user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        startDate.isPresent() ? LocalDate.parse(startDate.get()) : null,
                        endDate.isPresent() ? LocalDate.parse(endDate.get()) : null,
                        amount);

                sumOfExpenses.add(sumOfExpense);
            });
            return sumOfExpenses;
        }
    }
}
