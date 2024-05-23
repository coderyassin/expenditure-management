package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.SavingsDto;

public class SavingsMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;

    public SavingsMapping(Class<T> clazz,
                         ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public SavingsDto toDto(T obj) {
        return modelMapper.map(obj, SavingsDto.class);
    }

    public T fromDto(SavingsDto savingsDto) {
        return modelMapper.map(savingsDto, clazz);
    }

}
