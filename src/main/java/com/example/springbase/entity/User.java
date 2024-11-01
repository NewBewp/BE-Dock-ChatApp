package com.example.springbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_user")
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends TimeInfoEntityDefine implements Serializable, UserDetails {
    String username;
    String password;
    @Column(nullable = false)
    String email;
    String phoneNumber;
    String lastName;
    String firstName;
    String avatarURL;
    boolean isEmailVerified = false;
    boolean isOnline = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Workspace> workspaces;

    // @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name = "tbl_role_account", joinColumns = @JoinColumn(name =
    // "role_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    //
    // private Collection<Role> roles;
    //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // if (roles == null) {
        // return Collections.emptyList();
        // }
        // // Convert permissions to granted authority
        // // return roles.stream()
        // // .flatMap(role -> role.getPermissions().stream())
        // // .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        // // .toList();
        // return roles.stream().map(role -> new
        // SimpleGrantedAuthority(role.getName())).toList();
        return null;
    }
}
