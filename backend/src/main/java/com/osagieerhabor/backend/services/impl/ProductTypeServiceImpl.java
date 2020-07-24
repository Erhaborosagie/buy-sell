package com.osagieerhabor.backend.services.impl;

import com.osagieerhabor.backend.dto.ProductTypeDto;
import com.osagieerhabor.backend.exceptions.ResourceNotFoundException;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.model.ProductType;
import com.osagieerhabor.backend.repositories.ProductTypeRepository;
import com.osagieerhabor.backend.services.CategoryService;
import com.osagieerhabor.backend.services.ProductTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    private ProductTypeRepository productTypeRepository;
    private CategoryService categoryService;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository, CategoryService categoryService) {
        this.productTypeRepository = productTypeRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductType> getProductTypeByCategory(Category category) {
        return productTypeRepository.findByCategory(category);
    }

    @Override
    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ProductType addProductType(ProductTypeDto productTypeDto) {
        Category category = categoryService.findById(productTypeDto.getCategory_id());
        if (category == null)
            throw new ResourceNotFoundException(productTypeDto.getCategory_id() + "is an invalid category id");
        ProductType productType = productTypeDto.toProductType();
        productType.setCategory(category);
        return productTypeRepository.saveAndFlush(productType);
    }
}
