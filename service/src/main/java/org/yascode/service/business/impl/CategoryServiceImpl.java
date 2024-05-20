package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Category;
import org.yascode.persistence.repository.CategoryRepository;
import org.yascode.service.business.CategoryService;
import org.yascode.shared.dto.CategoryDto;
import org.yascode.shared.mapper.CategoryMapping;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private LogRecord logRecord = new LogRecord(Level.INFO, null);
    private final CategoryRepository categoryRepository;
    private final CategoryMapping<Category> categoryDtoToUser;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapping<Category> categoryDtoToUser) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoToUser = categoryDtoToUser;
    }

    /**
     * @return The list of categories
     */
    @Override
    public List<CategoryDto> allCategories() {
        return categoryRepository.findAll()
                .stream().map(categoryDtoToUser::toDto)
                .toList();
    }
}
