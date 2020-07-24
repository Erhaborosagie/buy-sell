package com.osagieerhabor.backend.controller;

import com.osagieerhabor.backend.dto.CategoryDto;
import com.osagieerhabor.backend.exceptions.ResourceNotFoundException;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        if (null != categoryDto.getParentId()){
            Category category = categoryService.findById(categoryDto.getParentId());
            if (category == null) {
                throw new ResourceNotFoundException("No category with id " + categoryDto.getParentId());
            }else {
                categoryDto.setParent(category);
            }
        }
        return ResponseEntity.ok(categoryService.addCategory(categoryDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if (category == null)
            throw new ResourceNotFoundException("id: " + id);
        return ResponseEntity.ok(categoryService.findById(id));
    }
}
