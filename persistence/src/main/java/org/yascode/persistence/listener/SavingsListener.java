package org.yascode.persistence.listener;

import jakarta.persistence.PrePersist;
import org.yascode.persistence.entity.Savings;

import java.time.LocalDate;
import java.util.Optional;

public class SavingsListener {

    @PrePersist
    void onPrePersist(Savings savings) {
        if(Optional.ofNullable(savings.getStartDate()).isEmpty())
            savings.setStartDate(LocalDate.now());
    }

}
