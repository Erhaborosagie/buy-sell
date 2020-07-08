package com.osagieerhabor.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.osagieerhabor.backend.enums.EnabledStatus;
import com.osagieerhabor.backend.utils.StringUtils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category extends BaseModel<Category> {

    @NotNull
    @Column(unique=true, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Category> children;

    @NotNull
    @Column(columnDefinition = "varchar(255) default 'fa fa-circle'")
    private String icon;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnabledStatus enabled = EnabledStatus.DISABLED;

    @NotNull
    @Column(unique = true, length = 50)
    private String slug;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category object = (Category) o;

        return getId().equals(object.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @PrePersist
    public void saving() {
        slug = StringUtils.generateSlug(name);
    }

    @JsonIgnore
    public Set<Category> getChildren() {
        return children;
    }

    @JsonIgnore
    public Category getParent(){
        return parent;
    }
}
