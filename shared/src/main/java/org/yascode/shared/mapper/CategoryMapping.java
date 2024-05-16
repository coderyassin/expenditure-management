package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.CategoryDto;

public class CategoryMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;

    public CategoryMapping(Class<T> clazz,
                           ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public CategoryDto toDto(T obj) {
        return modelMapper.map(obj, CategoryDto.class);
    }

    public T fromDto(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, clazz);
    }


}
