package com.example.springbase.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "tbl_user")
@DynamicInsert
public class User extends TimeInfoEntityDefine implements Serializable, UserDetails {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    private String phoneNumber;
    private String lastName;
    private String firstName;
    private String avatarURL;
    private boolean isEmailVerified = false;
    private boolean isOnline = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_account", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))

    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null) {
            return Collections.emptyList();
        }
        // Convert permissions to granted authority
        // return roles.stream()
        // .flatMap(role -> role.getPermissions().stream())
        // .map(permission -> new SimpleGrantedAuthority(permission.getName()))
        // .toList();
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }
}
