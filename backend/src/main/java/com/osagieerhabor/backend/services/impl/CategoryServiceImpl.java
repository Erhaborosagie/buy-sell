package com.osagieerhabor.backend.services.impl;

import com.osagieerhabor.backend.dto.CategoryDto;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.repositories.CategoryRepository;
import com.osagieerhabor.backend.services.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Category addCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toCategory();
        return categoryRepository.saveAndFlush(category);
    }
}
