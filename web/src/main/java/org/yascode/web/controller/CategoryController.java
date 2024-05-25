package org.yascode.web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.service.business.CategoryService;
import org.yascode.shared.dto.CategoryDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"/all"})
    public List<CategoryDto> allCategories() {
        return categoryService.allCategories();
    }

    @GetMapping("/export")
    public void exportCategoriesToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"categories.csv\"");

        List<CategoryDto> categories = categoryService.allCategories();

        try (PrintWriter writer = response.getWriter();
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Description"))) {
            for (CategoryDto category : categories) {
                csvPrinter.printRecord(category.getName(), category.getDescription());
            }
            csvPrinter.flush();
        }
    }

}
