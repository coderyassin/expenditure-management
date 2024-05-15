package org.yascode.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.persistence.entity.Expense;
import org.yascode.service.business.ExpenseService;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(value = {"/all"})
    List<Expense> allExpenses() {
        return expenseService.allExpenses();
    }
}
