package com.example.springbase.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "tbl_workspace")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Workspace extends EntityDefine {
    @Column(nullable = false, unique = true)
    String name; // Tên của workspace
    String description; // Mô tả về workspace

    @OneToMany(mappedBy = "workspaces", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Channel> channels;
}
