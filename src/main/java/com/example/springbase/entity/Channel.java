package com.example.springbase.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_channel")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Channel extends EntityDefine {
    @Column(nullable = false)
    String name; // Tên của channel

    String description;

    Boolean is_private; // kenh ca nhan ?

    @ManyToOne
    @JoinColumn(name = "workspace_id")
    Workspace workspace;
}
