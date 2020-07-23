package com.osagieerhabor.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.osagieerhabor.backend.enums.EnabledStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
@JsonInclude(JsonInclude.Include.ALWAYS)
public abstract class BaseModel<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnabledStatus enabled = EnabledStatus.DISABLED;
}
