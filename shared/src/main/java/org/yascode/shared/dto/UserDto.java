package org.yascode.shared.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    //private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate creationDate;

}
