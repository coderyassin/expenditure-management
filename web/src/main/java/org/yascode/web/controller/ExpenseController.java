package org.yascode.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yascode.service.business.ExpenseService;
import org.yascode.shared.dto.ExpenseDto;
import org.yascode.shared.model.SumOfExpenses;
import org.yascode.shared.requestBody.ExpenseRequestBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping(value = {"/all"})
    ResponseEntity<List<ExpenseDto>> allExpenses() {
        return ResponseEntity.ok(expenseService.allExpenses());
    }

    @GetMapping(value = {"/expenseBetween"})
    ResponseEntity<List<ExpenseDto>> expenseBetween(@RequestParam(name = "idUser", required = false) Optional<Long> idUser,
                                    @RequestParam(name = "startDate", required = false) Optional<String> startDate,
                                    @RequestParam(name = "endDate", required = false) Optional<String> endDate) {
        return ResponseEntity.ok(expenseService.expenseBetween(idUser, startDate, endDate));
    }

    @GetMapping(value = {"/sumOfExpenses"})
    ResponseEntity<List<SumOfExpenses>> sumOfExpenses(@RequestParam(name = "idUser", required = false) Optional<Long> idUser,
                                                @RequestParam(name = "startDate", required = false) Optional<String> startDate,
                                                @RequestParam(name = "endDate", required = false) Optional<String> endDate) {
        return ResponseEntity.ok(expenseService.sumOfExpenses(idUser, startDate, endDate));
    }

    @PostMapping(value = "/addExpense/{idUser}/{categoryId}")
    ResponseEntity<?> addExpense(@PathVariable(name = "idUser") String idUser,
                                 @PathVariable(name = "categoryId") String categoryId,
                                 @RequestBody ExpenseRequestBody expenseRequestBody) throws Exception {
        return ResponseEntity.ok(expenseService.addExpense(idUser, categoryId, expenseRequestBody));
    }

}
