package org.yascode.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.yascode.shared.enumeration.Source;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeDto {

    @JsonIgnore
    private Long id;
    private Double amount;
    private Source source;
    private LocalDate incomeDate;
    private LocalDate endDate;
    private UserDto user;
    @JsonIgnore
    private SavingsDto savings;
}
