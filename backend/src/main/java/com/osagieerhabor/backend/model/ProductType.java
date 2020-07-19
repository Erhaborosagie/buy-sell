package com.osagieerhabor.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class ProductType extends BaseModel<ProductType> {
    @NotNull
    @Column(unique=true, length = 50)
    private String name;

    @NotNull
    @ManyToOne
    private Category category;
}
