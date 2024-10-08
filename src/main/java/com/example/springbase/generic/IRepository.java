package com.example.springbase.generic;

import com.example.springbase.entity.EntityDefine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface IRepository<T extends EntityDefine, ID> extends JpaRepository<T, ID> {
    Collection<T> findAllByIsDeletedFalse();
}
