package org.yascode.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.ExpenseService;
import org.yascode.shared.dto.ExpenseDto;

import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(value = {"/all"})
    List<ExpenseDto> allExpenses() {
        return expenseService.allExpenses();
    }
}
