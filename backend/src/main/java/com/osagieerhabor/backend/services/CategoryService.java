package com.osagieerhabor.backend.services;

import com.osagieerhabor.backend.dto.CategoryDto;
import com.osagieerhabor.backend.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findBySlug(String slug);
    Category findById(Long id);
    Category addCategory(CategoryDto category);
}
