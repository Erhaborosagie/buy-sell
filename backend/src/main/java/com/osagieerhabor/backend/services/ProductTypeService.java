package com.osagieerhabor.backend.services;

import com.osagieerhabor.backend.dto.ProductTypeDto;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.model.ProductType;

import java.util.List;


public interface ProductTypeService {
    List<ProductType> getProductTypeByCategory(Category category);
    List<ProductType> getAllProductType();
    ProductType findById(Long id);
    ProductType addProductType(ProductTypeDto productType);
}
