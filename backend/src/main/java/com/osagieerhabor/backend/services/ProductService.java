package com.osagieerhabor.backend.services;

import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    Page<Product> findAll(int page, int size, String param, String dir);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void delete(Long id);
    Product findByCode(String code);
    void changeStatus(EnabledStatus status, Long id);
    List<Product> searchProduct(String keyword);
    Page<Product> findAllByEnabled(int page, int size, String param, String dir);
}
