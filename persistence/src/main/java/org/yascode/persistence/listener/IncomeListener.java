package org.yascode.persistence.listener;

import jakarta.persistence.PrePersist;
import org.yascode.persistence.entity.Income;

import java.util.Optional;

public class IncomeListener {

    @PrePersist
    void onPrePersist(Income income) {
        Optional.ofNullable(income.getIncomeDate())
                .ifPresent(incomeDate -> income.setEndDate(incomeDate.plusMonths(1).minusDays(1)));
    }

}
