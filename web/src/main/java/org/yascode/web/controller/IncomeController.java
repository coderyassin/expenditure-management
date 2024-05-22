package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.IncomeService;

@RestController
@RequestMapping(value = {"/incomes"})
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping(value = {"/"})
    public ResponseEntity<?> allIncomes() {
        return ResponseEntity.ok(incomeService.allIncomes());
    }
}
