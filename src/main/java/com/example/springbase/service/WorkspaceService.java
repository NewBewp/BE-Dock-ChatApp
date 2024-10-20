package com.example.springbase.service;

import java.util.List;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IService;

public interface WorkspaceService extends IService<Workspace, String> {
    Workspace createWorkspace (WorkspaceRequest dto);
    List<WorkspaceRequest> getAllWorkspaces(); // Thêm phương thức lấy tất cả workspaces
    Workspace updateWorkspace(String id, WorkspaceRequest dto);
    Boolean deleteWorkspace(String id);
} 
