package com.osagieerhabor.backend.services.impl;


import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.model.Product;
import com.osagieerhabor.backend.repositories.ProductRepository;
import com.osagieerhabor.backend.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Page<Product> findAll(int page, int size, String param, String dir) {
        if (dir.equals("asc"))
            return productRepository.findAll(PageRequest.of(page, size, Sort.by(param).ascending()));
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(param).descending()));
    }

    @Override
    public Page<Product> findAllByEnabled(int page, int size, String param, String dir) {
        EnabledStatus status = EnabledStatus.ENABLED;
        if (dir.equals("asc"))
            return productRepository.findAllByEnabled(PageRequest.of(page, size, Sort.by(param).ascending()), status);
        return productRepository.findAllByEnabled(PageRequest.of(page, size, Sort.by(param).descending()), status);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        productRepository.updateProduct(product.getPrice(), product.getImage(), product.getDescription(), product.getId());
        return productRepository.findById(product.getId()).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public void changeStatus(EnabledStatus status, Long id) {
        productRepository.changeStatus(status, id);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword);
    }
}
