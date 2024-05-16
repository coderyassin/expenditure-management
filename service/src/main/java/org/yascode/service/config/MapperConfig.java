package org.yascode.service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yascode.persistence.entity.Category;
import org.yascode.persistence.entity.User;
import org.yascode.shared.mapper.CategoryMapping;
import org.yascode.shared.mapper.UserMapping;

@Configuration
public class MapperConfig {

    private final ModelMapper modelMapper;

    public MapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Bean
    public UserMapping<User> UserDtoToUser() {
        return new UserMapping<>(User.class, modelMapper);
    }

    @Bean
    public CategoryMapping<Category> CategoryDtoToUser() {
        return new CategoryMapping<>(Category.class, modelMapper);
    }

}
