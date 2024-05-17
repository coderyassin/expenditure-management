package org.yascode.shared.model;

import java.time.LocalDate;

public record SumOfExpenses(String firstName,
                            String lastName,
                            String email,
                            LocalDate startDate,
                            LocalDate endDate,
                            Double amount) {
}
