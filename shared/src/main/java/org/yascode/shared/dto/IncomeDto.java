package org.yascode.shared.dto;

import lombok.*;
import org.yascode.shared.enumeration.Source;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeDto {

    //private Long id;
    private Source source;
    private LocalDate incomeDate;
    private LocalDate endDate;
    private UserDto user;
}
