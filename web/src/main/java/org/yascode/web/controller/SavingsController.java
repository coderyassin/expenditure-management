package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.service.business.SavingsService;
import org.yascode.shared.dto.SavingsDto;

@RestController
@RequestMapping(value = {"/savings"})
public class SavingsController {

    private final SavingsService savingsService;


    public SavingsController(SavingsService savingsService) {
        this.savingsService = savingsService;
    }

    @GetMapping(value = {"/"})
    public ResponseEntity<?> allSavings() {
        return ResponseEntity.ok(savingsService.allIncomes());
    }

    @PostMapping
    public ResponseEntity<?> addSavings(@RequestBody SavingsDto savingsDto) {
        return ResponseEntity.ok(savingsService.saveSavings(savingsDto));
    }
}

