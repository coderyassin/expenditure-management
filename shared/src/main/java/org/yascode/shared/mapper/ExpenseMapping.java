package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.ExpenseDto;

public class ExpenseMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;


    public ExpenseMapping(Class<T> clazz,
                          ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public ExpenseDto toDto(T obj) {
        return modelMapper.map(obj, ExpenseDto.class);
    }

    public T fromDto(ExpenseDto expenseDto) {
        return modelMapper.map(expenseDto, clazz);
    }


}
