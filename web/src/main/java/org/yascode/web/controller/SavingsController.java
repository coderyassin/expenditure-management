package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.service.business.SavingsService;
import org.yascode.shared.dto.SavingsDto;

import java.time.YearMonth;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/savings"})
public class SavingsController {

    private final SavingsService savingsService;


    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    @GetMapping(value = {"/"})
    public ResponseEntity<?> allSavings() {
        return ResponseEntity.ok(savingsService.allSavings());
    }

    @PostMapping
    public ResponseEntity<?> addSavings(@RequestBody SavingsDto savingsDto) {
        return ResponseEntity.ok(savingsService.saveSavings(savingsDto));
    }

    @GetMapping(value = {"/{userId}"})
    public ResponseEntity<?> allSavings(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok(savingsService.allSavings(userId));
    }

    @GetMapping(value = {"sum/{userId}/{startDate}/{endDate}",
            "sum/{userId}/{startDate}",
            "sum/{userId}"})
    public ResponseEntity<?> totalSavingsBetween(@PathVariable(name = "userId") String userId,
                                                 @PathVariable(name = "startDate", required = false) Optional<YearMonth> startDate,
                                                 @PathVariable(name = "endDate", required = false) Optional<YearMonth> endDate) {
        return ResponseEntity.ok(savingsService.totalSavingsBetween(userId, startDate, endDate));
    }

}

