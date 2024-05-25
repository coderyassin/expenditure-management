package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.IncomeService;

import java.time.Month;
import java.time.Year;
import java.util.Optional;

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

    @GetMapping(value = {"value/{userId}/{year}/{monthValue}",
                         "name/{userId}/{year}/{month}",
                         "/{userId}/{year}",
                         "/{userId}"})
    public ResponseEntity<?> incomesByYearAndMonth(@PathVariable("userId") String userId,
                                                   @PathVariable(value = "year", required = false) Optional<Year> year,
                                                   @PathVariable(value = "monthValue", required = false) Optional<Integer> monthValue,
                                                   @PathVariable(value = "month", required = false) Optional<Month> month) {
        return ResponseEntity.ok(incomeService.incomesByYearOrMonth(userId, year, month, monthValue));
    }

}
