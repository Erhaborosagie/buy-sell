package com.osagieerhabor.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Supplier extends BaseModel<Supplier>{
    private String name;

    @OneToMany(mappedBy = "suppliers")
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier object = (Supplier) o;

        return getId().equals(object.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
