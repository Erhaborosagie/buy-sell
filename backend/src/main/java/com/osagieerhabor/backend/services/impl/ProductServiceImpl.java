package com.osagieerhabor.backend.services.impl;


import com.osagieerhabor.backend.model.Product;
import com.osagieerhabor.backend.repositories.ProductRepository;
import com.osagieerhabor.backend.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }
}
