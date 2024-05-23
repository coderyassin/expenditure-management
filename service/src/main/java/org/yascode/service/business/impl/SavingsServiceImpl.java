package org.yascode.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Savings;
import org.yascode.persistence.repository.SavingsRepository;
import org.yascode.service.business.SavingsService;
import org.yascode.shared.dto.SavingsDto;
import org.yascode.shared.mapper.SavingsMapping;

import java.util.List;

@Service
public class SavingsServiceImpl implements SavingsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final SavingsRepository savingsRepository;
    private final SavingsMapping<Savings> savingsDtoToIncome;

    public SavingsServiceImpl(SavingsRepository savingsRepository,
                              SavingsMapping<Savings> savingsDtoToIncome) {
        this.savingsRepository = savingsRepository;
        this.savingsDtoToIncome = savingsDtoToIncome;
    }

    /**
     * @param savingsDto
     * @return
     */
    @Override
    public SavingsDto saveSavings(SavingsDto savingsDto) {
        return savingsDtoToIncome.toDto(savingsRepository.save(savingsDtoToIncome.fromDto(savingsDto)));
    }

    /**
     * @return
     */
    @Override
    public List<SavingsDto> allIncomes() {
        return savingsRepository.findAll()
                .stream()
                .map(savingsDtoToIncome::toDto)
                .toList();
    }
}
