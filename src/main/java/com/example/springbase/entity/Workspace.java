package com.example.springbase.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    String name;
    String description;
    String avatarURL;
    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Channel> channels;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}