package org.yascode.persistence.listener;

import jakarta.persistence.PrePersist;
import org.yascode.persistence.entity.Savings;

import java.time.LocalDate;

public class SavingsListener {

    @PrePersist
    void onPrePersist(Savings savings) {
        savings.setStartDate(LocalDate.now());
    }

}
