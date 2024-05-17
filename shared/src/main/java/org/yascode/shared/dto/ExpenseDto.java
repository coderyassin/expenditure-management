package org.yascode.shared.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    //private Long id;
    private Double amount;
    private LocalDate expenseDate;
    private String description;
    private CategoryDto category;
    private UserDto user;

}
