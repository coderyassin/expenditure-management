package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.service.business.BudgetService;
import org.yascode.shared.dto.BudgetDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = {"/budgetsForPeriod/{idUser}"})
    public ResponseEntity<List<BudgetDto>>
        budgetsForAPeriod(@PathVariable("idUser") Optional<String> idUser,
                          @RequestParam("startDate") Optional<LocalDate> startDate,
                          @RequestParam("endDate") Optional<LocalDate> endDate) {
        return ResponseEntity.ok(budgetService.budgetsForAPeriod(idUser, startDate, endDate));
    }
}
