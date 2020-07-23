package com.osagieerhabor.backend.dto;

import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.model.ProductType;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class ProductTypeDto {
    @NotNull
    private String name;

    @NotNull
    private Long category_id;

    private Category category;



    public ProductType toProductType(){
        ProductType productType = new ProductType();
        productType.setName(name);
        productType.setCategory(category);
        return productType;
    }
}
