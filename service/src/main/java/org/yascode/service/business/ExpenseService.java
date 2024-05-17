package org.yascode.service.business;

import org.yascode.shared.dto.ExpenseDto;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    List<ExpenseDto> allExpenses();
    List<ExpenseDto> expenseBetween(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate);

    Double sumOfExpenses(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate);
}
