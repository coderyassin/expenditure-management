package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Budget;
import org.yascode.persistence.entity.User;
import org.yascode.persistence.repository.BudgetRepository;
import org.yascode.persistence.repository.UserRepository;
import org.yascode.persistence.repository.specification.BudgetSpec;
import org.yascode.service.business.BudgetService;
import org.yascode.shared.dto.BudgetDto;
import org.yascode.shared.exception.ResourceCannotBeEmptyException;
import org.yascode.shared.exception.ResourceNotFoundException;
import org.yascode.shared.mapper.BudgetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final BudgetMapping<Budget> budgetMapping;

    public BudgetServiceImpl(BudgetRepository budgetRepository,
                             UserRepository userRepository,
                             BudgetMapping<Budget> budgetMapping) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.budgetMapping = budgetMapping;
    }

    /**
     * @return all budgets
     */
    @Override
    public List<BudgetDto> getAllBudgets() {
        return budgetRepository.findAll()
                .stream()
                .map(budgetMapping::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param idUser
     * @param startDate
     * @param endDate
     * @return list of budgets for a period between startDate and endDate
     */
    @Override
    public List<BudgetDto> budgetsForAPeriod(Optional<String> idUser,
                                             Optional<LocalDate> startDate,
                                             Optional<LocalDate> endDate) {
        checkThatTheUserExists(idUser);
        return budgetRepository.findAll(BudgetSpec.budgetsBetween(idUser, startDate, endDate))
                .stream()
                .map(budgetMapping::toDto)
                .toList();
    }

    private void checkThatTheUserExists(Optional<String> idUser) {

        Optional.ofNullable(idUser).ifPresentOrElse(userId -> {
            if(userRepository.findById(Long.valueOf(userId.get())).isEmpty()) {
                throw new ResourceNotFoundException(String.format("User with ID %s does not exist", userId.get()));
            }
        }, () -> {throw new ResourceCannotBeEmptyException("IdUser cannot be empty");});
    }
}
