package org.yascode.shared.requestBody;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record RangeDate(@NotNull @NotEmpty String idUser,
                        @NotNull @NotEmpty String categoryId,
                        String startDate,
                        String endDate) implements Serializable {
}
