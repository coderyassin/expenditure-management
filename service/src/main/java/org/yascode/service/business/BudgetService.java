package org.yascode.service.business;

import org.yascode.shared.dto.BudgetDto;

import java.util.List;

public interface BudgetService {

    List<BudgetDto> getAllBudgets();

}
