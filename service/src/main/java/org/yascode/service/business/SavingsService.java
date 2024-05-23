package org.yascode.service.business;

import org.yascode.persistence.entity.Savings;
import org.yascode.shared.dto.SavingsDto;

import java.util.List;

public interface SavingsService {

    SavingsDto saveSavings(final SavingsDto savingsDto);

    List<SavingsDto> allIncomes();
}
