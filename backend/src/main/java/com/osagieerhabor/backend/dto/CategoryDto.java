package com.osagieerhabor.backend.dto;

import com.osagieerhabor.backend.model.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class CategoryDto {
    @NotNull
    private String name;

    @NotNull
    private String icon;

    private Long parentId;

    private Category parent;
    private Set<Category> children;

    public Category toCategory(){
        Category category = new Category();
        category.setName(name);
        category.setIcon(icon);
        category.setParent(parent);
        return category;
    }
}
