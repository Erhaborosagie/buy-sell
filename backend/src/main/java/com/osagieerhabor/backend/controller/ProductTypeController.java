package com.osagieerhabor.backend.controller;

import com.osagieerhabor.backend.dto.ProductTypeDto;
import com.osagieerhabor.backend.exceptions.CategoryNotFoundException;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.services.CategoryService;
import com.osagieerhabor.backend.services.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-type")
public class ProductTypeController {
    private final ProductTypeService productTypeService;
    private final CategoryService categoryService;

    public ProductTypeController(ProductTypeService productTypeService, CategoryService categoryService) {
        this.productTypeService = productTypeService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> addProductType(@RequestBody ProductTypeDto productTypeDto){
        Category category = categoryService.findById(productTypeDto.getCategory_id());
        if (category == null)
            throw new CategoryNotFoundException("No category with id " + productTypeDto.getCategory_id());
        productTypeDto.setCategory(category);
        return ResponseEntity.ok(productTypeService.addProductType(productTypeDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllProductType(){
        return ResponseEntity.ok(productTypeService.getAllProductType());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(productTypeService.findById(id));
    }

    @GetMapping("category/{id}")
    public ResponseEntity<?> getProductTypeByCategoryId(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category == null)
            throw new CategoryNotFoundException(id + "is an invalid category id");
        return ResponseEntity.ok(productTypeService.getProductTypeByCategory(category));
    }
}
