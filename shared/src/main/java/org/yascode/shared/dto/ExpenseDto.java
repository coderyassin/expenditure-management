package org.yascode.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto implements Serializable {

    //private Long id;
    private Double amount;
    private LocalDate expenseDate;
    private String description;
    private CategoryDto category;
    @JsonIgnore
    private BudgetDto budget;

}
