package org.yascode.service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yascode.persistence.entity.*;
import org.yascode.shared.mapper.*;

@Configuration
public class MapperConfig {

    private final ModelMapper modelMapper;

    public MapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Bean
    public UserMapping<User> userDtoToUser() {
        return new UserMapping<>(User.class, modelMapper);
    }

    @Bean
    public CategoryMapping<Category> categoryDtoToUser() {
        return new CategoryMapping<>(Category.class, modelMapper);
    }

    @Bean
    public ExpenseMapping<Expense> expenseDtoToExpense() {
        return new ExpenseMapping<>(Expense.class, modelMapper);
    }

    @Bean
    public BudgetMapping<Budget> budgetDtoToBudget() {
        return new BudgetMapping<>(Budget.class, modelMapper);
    }

    @Bean
    public IncomeMapping<Income> incomeDtoToIncome() {
        return new IncomeMapping<>(Income.class, modelMapper);
    }

}
