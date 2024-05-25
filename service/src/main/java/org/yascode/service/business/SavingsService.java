package org.yascode.service.business;

import org.yascode.shared.dto.SavingsDto;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface SavingsService {

    SavingsDto saveSavings(final SavingsDto savingsDto);

    List<SavingsDto> allSavings();

    List<SavingsDto> allSavings(String userId);

    double totalSavingsBetween(String userId, Optional<YearMonth> startDate, Optional<YearMonth> endDate);
}
