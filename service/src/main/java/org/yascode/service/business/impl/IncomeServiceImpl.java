package org.yascode.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Income;
import org.yascode.persistence.repository.IncomeRepository;
import org.yascode.service.business.IncomeService;
import org.yascode.shared.dto.IncomeDto;
import org.yascode.shared.mapper.IncomeMapping;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final IncomeRepository incomeRepository;
    private final IncomeMapping<Income> incomeDtoToIncome;

    public IncomeServiceImpl(IncomeRepository incomeRepository,
                             IncomeMapping<Income> incomeDtoToIncome) {
        this.incomeRepository = incomeRepository;
        this.incomeDtoToIncome = incomeDtoToIncome;
    }

    /**
     * @param income
     * @return the income that has been saved
     */
    @Override
    public IncomeDto saveIncome(Income income) {
        return incomeDtoToIncome.toDto(incomeRepository.save(income));
    }

    @Override
    public List<IncomeDto> allIncomes() {
        return incomeRepository.findAll()
                .stream()
                .map(incomeDtoToIncome::toDto)
                .toList();
    }


}
