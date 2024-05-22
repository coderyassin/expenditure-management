package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.IncomeDto;

public class IncomeMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;

    public IncomeMapping(Class<T> clazz,
                         ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public IncomeDto toDto(T obj) {
        return modelMapper.map(obj, IncomeDto.class);
    }

    public T fromDto(IncomeDto incomeDto) {
        return modelMapper.map(incomeDto, clazz);
    }

}
