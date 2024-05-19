package org.yascode.shared.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {

    //private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate creationDate;

}
