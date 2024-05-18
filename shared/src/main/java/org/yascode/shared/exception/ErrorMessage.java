package org.yascode.shared.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;


}
