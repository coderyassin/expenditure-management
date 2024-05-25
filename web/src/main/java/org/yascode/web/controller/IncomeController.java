package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.IncomeService;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
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

    @GetMapping(value = {"/between/{userId}/{year}/{startMonth}/{endMonth}",
                         "/between/{userId}/{startMonth}/{endMonth}"})
    public ResponseEntity<?> incomesBetween(@PathVariable(value = "userId") String userId,
                                            @PathVariable(value = "year", required = false) Optional<Year> year,
                                            @PathVariable(value = "startMonth", required = false) Optional<Integer> startMonth,
                                            @PathVariable(value = "endMonth", required = false) Optional<Integer> endMonth) {
        return ResponseEntity.ok(incomeService.incomesBetween(userId, year, startMonth, endMonth));
    }

    @GetMapping(value = {"sum/{userId}/{startDate}/{endDate}",
                         "sum/{userId}/{startDate}",
                         "sum/{userId}"})
    public ResponseEntity<?> totalIncomesBetween(@PathVariable(name = "userId") String userId,
                                                 @PathVariable(name = "startDate", required = false) Optional<YearMonth> startDate,
                                                 @PathVariable(name = "endDate", required = false) Optional<YearMonth> endDate) {
        return ResponseEntity.ok(incomeService.totalIncomesBetween(userId, startDate, endDate));
    }

}
