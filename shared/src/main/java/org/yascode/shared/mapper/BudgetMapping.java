package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.BudgetDto;

public class BudgetMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;

    public BudgetMapping(Class<T> clazz,
                         ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public BudgetDto toDto(T obj) {
        return modelMapper.map(obj, BudgetDto.class);
    }

    public T fromDto(BudgetDto budgetDto) {
        return modelMapper.map(budgetDto, clazz);
    }

}
