package com.example.springbase.service;

import java.util.List;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IService;

public interface WorkspaceService extends IService<Workspace, String> {
    WorkspaceResponse createWorkspace (WorkspaceRequest request);
    List<WorkspaceResponse> getAllWorkspaces(); // Thêm phương thức lấy tất cả workspaces
    WorkspaceResponse updateWorkspace(String id, WorkspaceRequest request);
    Boolean deleteWorkspace(String id);
    WorkspaceResponse findWorkspaceById(String id);

} 
