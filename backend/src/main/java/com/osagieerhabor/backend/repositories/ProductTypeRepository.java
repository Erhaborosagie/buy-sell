package com.osagieerhabor.backend.repositories;

import com.osagieerhabor.backend.model.Category;
import com.osagieerhabor.backend.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> findByCategory(@NotNull Category category);
}
