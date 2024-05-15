package org.yascode.service.business;

import org.yascode.persistence.entity.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> allExpenses();

}
