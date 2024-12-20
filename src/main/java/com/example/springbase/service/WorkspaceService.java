package com.example.springbase.service;

import java.util.List;
import java.util.Set;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.WorkspaceDetailResponse;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IService;

public interface WorkspaceService extends IService<Workspace, String> {
    // WorkspaceResponse createWorkspace (WorkspaceRequest request);
    Set<WorkspaceResponse> getAllWorkspaces(); 
    WorkspaceResponse updateWorkspace(String id, WorkspaceRequest request);
    Boolean deleteWorkspace(String id);
    WorkspaceResponse findWorkspaceById(String id);
    WorkspaceDetailResponse findWorkspaceEntityById(String id);
    WorkspaceResponse createWorkspaceByUser(String userId, WorkspaceRequest request);
    Set<WorkspaceResponse> findWorkspaceByUserId(String userId);
} 
