package com.example.springbase.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@MappedSuperclass
public abstract class EntityDefine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private String id;
    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("b'0'")
    private Boolean isDeleted;
}
