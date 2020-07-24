package com.osagieerhabor.backend.controller;

import com.osagieerhabor.backend.dto.GeneralDto;
import com.osagieerhabor.backend.dto.ProductDto;
import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.exceptions.BuySellException;
import com.osagieerhabor.backend.exceptions.ProductNotFoundException;
import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.model.Product;
import com.osagieerhabor.backend.model.ProductType;
import com.osagieerhabor.backend.model.Supplier;
import com.osagieerhabor.backend.repositories.SupplierRepository;
import com.osagieerhabor.backend.services.ProductService;
import com.osagieerhabor.backend.services.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private ProductService productService;
    private SupplierRepository supplierRepository;
    private ProductTypeService productTypeService;

    public ProductController(
            ProductService productService, SupplierRepository supplierRepository,
            ProductTypeService productTypeService) {
        this.productService = productService;
        this.supplierRepository = supplierRepository;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = productService.findById(id);

        if (product == null){
            throw  new ProductNotFoundException("No product with id " + id);
        }

        return ResponseEntity.ok(product);
    }

    @GetMapping
    ResponseEntity<Page<Product>> findAllEnabled(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam(value = "dir", required = false, defaultValue = "desc") String dir,
            @RequestParam(value = "keyword", required = false, defaultValue = "createdAt") String keyword) {
        return ResponseEntity.ok(productService.findAllByEnabled(page, pageSize, keyword, dir));
    }

    @PostMapping("/add/{supplier_id}")
    ResponseEntity<?> createProduct(
            @RequestBody @Valid ProductDto productDto,
            BindingResult bindingResult,
            @PathVariable Long supplier_id){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Bad request. Please fill all fields");
        }

        Supplier supplier = supplierRepository.findById(supplier_id).orElse(null);
        if (supplier == null)
            throw new BuySellException("No supplier with id -" + supplier_id);

        ProductType productType = productTypeService.findById(productDto.getProduct_type_id());
        if (productType == null)
            throw new BuySellException("No productType with id -" + productDto.getProduct_type_id());

        Category category = productType.getCategory();

        Product product = productDto.toProduct();
        product.setSupplier(supplier);
        product.setProductType(productType);
        product.setCategory(category);

        product = productService.createProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(location).body(product);

    }

    @PutMapping("/{id}")
    public Product updateProduct(Product product){
        return productService.updateProduct(product);
    }

    void delete(Long id){
        productService.delete(id);
    }

    Product findByCode(String code){
        return productService.findByCode(code);
    }

    @PostMapping("/{id}/status")
    ResponseEntity<?> changeStatus(@RequestBody GeneralDto generalDto, @PathVariable Long id){
        log.info(generalDto.getStatus().toString());
        if (generalDto.getStatus()== EnabledStatus.ENABLED || generalDto.getStatus()==EnabledStatus.DISABLED){
            Product product = productService.findById(id);
            if(product==null)
                throw new ProductNotFoundException("No product with id -" + id);
            productService.changeStatus(generalDto.getStatus(), id);
            return ResponseEntity.ok("Product status changed");
        }else return ResponseEntity.badRequest().body("Enter status as ENABLED or DISABLED");
    }

    @GetMapping("/search")
    List<Product> searchProduct(@RequestParam String keyword){
        return productService.searchProduct(keyword);
    }
}
