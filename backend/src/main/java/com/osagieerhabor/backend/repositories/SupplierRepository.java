package com.osagieerhabor.backend.repositories;

import com.osagieerhabor.backend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
