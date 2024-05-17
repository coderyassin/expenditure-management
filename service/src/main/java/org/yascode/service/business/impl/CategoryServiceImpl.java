package org.yascode.service.business.impl;

import org.springframework.stereotype.Service;
import org.yascode.persistence.entity.Category;
import org.yascode.persistence.repository.CategoryRepository;
import org.yascode.service.business.CategoryService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@Service
public class CategoryServiceImpl implements CategoryService {

    private Logger LOGGER = Logger.getLogger(getClass().getName());
    private LogRecord logRecord = new LogRecord(Level.INFO, null);
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * @return The list of categories
     */
    @Override
    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }
}
