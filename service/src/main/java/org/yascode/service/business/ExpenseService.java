package org.yascode.service.business;

import org.yascode.shared.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    List<ExpenseDto> allExpenses();

}
