package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.BudgetService;
import org.yascode.shared.dto.BudgetDto;

import java.util.List;

@RestController
@RequestMapping(value = "/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }
}
