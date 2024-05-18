package org.yascode.shared.requestBody;

import java.time.LocalDate;

public record ExpenseRequestBody(Double amount,
                                 LocalDate expenseDate,
                                 String description) {

}
