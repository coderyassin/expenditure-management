package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.yascode.shared.dto.UserDto;

public class UserMapping<T> {

    private final Class<T> clazz;
    private final ModelMapper modelMapper;

    public UserMapping(Class<T> clazz,
                       ModelMapper modelMapper) {
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    public UserDto toDto(T obj) {
        return modelMapper.map(obj, UserDto.class);
    }

    public T fromDto(UserDto userDto) {
        return modelMapper.map(userDto, clazz);
    }

}
