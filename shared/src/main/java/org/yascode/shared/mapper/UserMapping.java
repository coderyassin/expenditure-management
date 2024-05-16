package org.yascode.shared.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.yascode.shared.dto.UserDto;

@Component
public class UserMapping<T> {

    private final ModelMapper modelMapper;

    public UserMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto toDto(T obj) {
        return modelMapper.map(obj, UserDto.class);
    }

}
