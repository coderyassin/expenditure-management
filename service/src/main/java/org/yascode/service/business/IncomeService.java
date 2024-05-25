package org.yascode.service.business;

import org.yascode.persistence.entity.Income;
import org.yascode.shared.dto.IncomeDto;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface IncomeService {

    IncomeDto saveIncome(final Income income);

    List<IncomeDto> allIncomes();

    List<IncomeDto> incomesByYearAndMonth(String userId, YearMonth yearMonth);

    List<IncomeDto> incomesByYear(String userId, Year year);

    List<IncomeDto> incomesByYearOrMonth(String userId, Optional<Year> year, Optional<Month> month);

    List<IncomeDto> incomesByYearOrMonthValue(String userId, Optional<Year> year, Optional<Integer> month);

    List<IncomeDto> incomesByYearOrMonth(String userId, Optional<Year> year, Optional<Month> month, Optional<Integer> monthValue);
}
