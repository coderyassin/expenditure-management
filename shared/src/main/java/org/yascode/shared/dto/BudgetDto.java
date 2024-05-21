package org.yascode.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BudgetDto implements Serializable {
    private Long id;
    private Double amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private UserDto user;
    List<CategoryDto> categories;
    @JsonIgnore
    private List<ExpenseDto> expenses;
}