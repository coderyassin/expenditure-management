package org.yascode.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Income;
import org.yascode.persistence.repository.IncomeRepository;
import org.yascode.persistence.repository.specification.IncomeSpec;
import org.yascode.service.business.IncomeService;
import org.yascode.shared.dto.IncomeDto;
import org.yascode.shared.mapper.IncomeMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

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

    /**
     * @param userId
     * @param yearMonth
     * @return
     */
    @Override
    public List<IncomeDto> incomesByYearAndMonth(String userId, YearMonth yearMonth) {
        LocalDate startDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        LocalDate endDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), yearMonth.lengthOfMonth());
        return incomeRepository.findAll(IncomeSpec.incomesByYearAndMonth(userId, startDate, endDate))
                .stream().map(incomeDtoToIncome::toDto)
                .toList();
    }

    /**
     * @param userId
     * @param year
     * @return
     */
    @Override
    public List<IncomeDto> incomesByYear(String userId, Year year) {
        LocalDate startDate = LocalDate.of(year.getValue(), Month.JANUARY.getValue(), 1);
        LocalDate endDate = LocalDate.of(year.getValue(), Month.DECEMBER.getValue(), 31);
        return incomeRepository.findAll(IncomeSpec.incomesByYearAndMonth(userId, startDate, endDate))
                .stream().map(incomeDtoToIncome::toDto)
                .toList();
    }

    /**
     * @param userId
     * @param year
     * @param month
     * @return
     */
    @Override
    public List<IncomeDto> incomesByYearOrMonth(String userId, Optional<Year> year, Optional<Month> month) {
        return month.isPresent() ?
            incomesByYearAndMonth(userId, YearMonth.of(year.orElse(Year.now()).getValue(), month.get().getValue())) :
            incomesByYear(userId, year.orElse(Year.now()));
    }

    /**
     * @param userId
     * @param year
     * @param month
     * @return
     */
    @Override
    public List<IncomeDto> incomesByYearOrMonthValue(String userId, Optional<Year> year, Optional<Integer> month) {
        return month.isPresent() ?
                incomesByYearAndMonth(userId, YearMonth.of(year.orElse(Year.now()).getValue(), month.get())) :
                incomesByYear(userId, year.orElse(Year.now()));
    }

    /**
     * @param userId
     * @param year
     * @param month
     * @param monthValue
     * @return
     */
    @Override
    public List<IncomeDto> incomesByYearOrMonth(String userId, Optional<Year> year, Optional<Month> month, Optional<Integer> monthValue) {
        return month.isPresent() ? incomesByYearOrMonth(userId, year, month): incomesByYearOrMonthValue(userId, year, monthValue);
    }


}
