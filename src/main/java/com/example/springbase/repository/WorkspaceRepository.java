package com.example.springbase.repository;

import org.springframework.stereotype.Repository;

import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IRepository;

@Repository
public interface WorkspaceRepository extends IRepository<Workspace, String>{
    
}
