package org.yascode.service.business;

import org.yascode.persistence.entity.Income;
import org.yascode.shared.dto.IncomeDto;

import java.util.List;

public interface IncomeService {

    IncomeDto saveIncome(final Income income);

    List<IncomeDto> allIncomes();
}
