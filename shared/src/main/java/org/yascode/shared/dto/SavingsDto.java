package org.yascode.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsDto {

    @JsonIgnore
    private Long id;
    private Double amount;
    private String description;
    private LocalDate startDate;
    private IncomeDto income;

}
