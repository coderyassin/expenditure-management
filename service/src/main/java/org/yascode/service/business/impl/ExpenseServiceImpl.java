package org.yascode.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Category;
import org.yascode.persistence.entity.Expense;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.CategoryRepository;
import org.yascode.persistence.repository.ExpenseRepository;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.persistence.repository.specification.ExpenseSpec;
import org.yascode.service.business.ExpenseService;
import org.yascode.shared.dto.ExpenseDto;
import org.yascode.shared.exception.ResourceNotFoundException;
import org.yascode.shared.mapper.ExpenseMapping;
import org.yascode.shared.model.CheckParams;
import org.yascode.shared.model.SumOfExpenses;
import org.yascode.shared.requestBody.ExpenseRequestBody;
import org.yascode.shared.requestBody.RangeDate;

import java.time.LocalDate;
import java.util.*;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseMapping<Expense> expenseDtoToExpense;
    private final CategoryRepository categoryRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              UserRepository userRepository,
                              ExpenseMapping<Expense> expenseDtoToExpense, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseDtoToExpense = expenseDtoToExpense;
        this.categoryRepository = categoryRepository;
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

    /**
     * @param idUser
     * @param categoryId
     * @param expenseRequestBody
     * @return returns the expense that has been saved
     */
    @Override
    public ExpenseDto addExpense(String idUser, String categoryId, ExpenseRequestBody expenseRequestBody) throws ResourceNotFoundException {
        requireNonNull(new CheckParams(idUser, "User ID cannot be null"),
                       new CheckParams(categoryId, "Category ID cannot be null"));

        User user = userRepository.findById(Long.parseLong(idUser))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %s not found", idUser)));
        Category category = categoryRepository.findById(Long.parseLong(categoryId))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Category with id %s not found", categoryId)));

        Expense expense = Expense.builder()
                .user(user)
                .category(category)
                .description(expenseRequestBody.description())
                .amount(expenseRequestBody.amount())
                .expenseDate(expenseRequestBody.expenseDate())
                .build();

        return expenseDtoToExpense.toDto(expenseRepository.save(expense));
    }

    /**
     * @param idUser
     * @param categoryId
     * @param startDate
     * @param endDate
     * @return List of a user's expenses between a date
     */
    @Override
    public List<ExpenseDto> filteringByCategory(String idUser, String categoryId, Optional<String> startDate, Optional<String> endDate) {
        requireNonNull(new CheckParams(idUser, "User ID cannot be null"),
                new CheckParams(categoryId, "Category ID cannot be null"));

        return expenseRepository.findAll(ExpenseSpec.filteringByCategory(idUser, categoryId, startDate.map(LocalDate::parse), endDate.map(LocalDate::parse)))
                .stream()
                .map(expenseDtoToExpense::toDto)
                .toList();
    }

    /**
     * @param rangeDate
     * @return List of a user's expenses between a date
     */
    @Override
    public List<ExpenseDto> filteringByCategory(RangeDate rangeDate) {
        requireNonNull(new CheckParams(rangeDate.idUser(), "User ID cannot be null"),
                new CheckParams(rangeDate.categoryId(), "Category ID cannot be null"));

        return expenseRepository.findAll(ExpenseSpec.filteringByCategory(rangeDate.idUser(), rangeDate.categoryId(), Optional.ofNullable(rangeDate.startDate()).map(LocalDate::parse), Optional.ofNullable(rangeDate.endDate()).map(LocalDate::parse)))
                .stream()
                .map(expenseDtoToExpense::toDto)
                .toList();
    }

    private void requireNonNull(CheckParams... params) {
        Arrays.stream(params)
                .forEach(param -> Objects.requireNonNull(param.object(), param.message()));
    }


}
