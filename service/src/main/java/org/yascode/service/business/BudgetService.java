package org.yascode.service.business;

import org.yascode.shared.dto.BudgetDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BudgetService {

    List<BudgetDto> getAllBudgets();
    List<BudgetDto> budgetsForAPeriod(Optional<String> idUser, Optional<LocalDate> startDate, Optional<LocalDate> endDate);

}
