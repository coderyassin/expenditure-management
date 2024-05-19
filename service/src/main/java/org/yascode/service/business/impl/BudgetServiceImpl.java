package org.yascode.service.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.yascode.persistence.repository.BudgetRepository;
import org.yascode.service.business.BudgetService;
import org.yascode.shared.dto.BudgetDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final ModelMapper modelMapper;

    public BudgetServiceImpl(BudgetRepository budgetRepository,
                             ModelMapper modelMapper) {
        this.budgetRepository = budgetRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * @return all budgets
     */
    @Override
    public List<BudgetDto> getAllBudgets() {
        return budgetRepository.findAll().stream()
                .map((budget) -> modelMapper.map(budget, BudgetDto.class)).collect(Collectors.toList());
    }
}
