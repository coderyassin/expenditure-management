package org.yascode.service.business;

import jakarta.validation.constraints.NotNull;
import org.yascode.shared.dto.ExpenseDto;
import org.yascode.shared.model.SumOfExpenses;
import org.yascode.shared.requestBody.ExpenseRequestBody;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    List<ExpenseDto> allExpenses();
    List<ExpenseDto> expenseBetween(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate);

    List<SumOfExpenses> sumOfExpenses(Optional<Long> idUser, Optional<String> startDate, Optional<String> endDate);

    ExpenseDto addExpense(@NotNull String idUser,
                          @NotNull String categoryId,
                          ExpenseRequestBody expenseRequestBody) throws Exception;
}
