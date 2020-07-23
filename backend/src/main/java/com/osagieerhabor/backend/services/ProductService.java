package com.osagieerhabor.backend.services;

import com.osagieerhabor.backend.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product findById(Long id);
    List<Product> findAll();
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void delete(Long id);
    Product findByCode(String code);
}
