package com.osagieerhabor.backend.repositories;

import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);

    Page<Product> findAllByEnabled(Pageable pageable, EnabledStatus status);

    @Modifying
    @Transactional
    @Query("update Product p set p.price = ?1, p.image = ?2, p.description = ?3 where p.id = ?4")
    void updateProduct(Double price, String image,String description, Long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.enabled = ?1 where p.id = ?2")
    void changeStatus(EnabledStatus status, Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
    List<Product> searchProduct(String keyword);
}
