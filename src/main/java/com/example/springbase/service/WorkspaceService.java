package com.example.springbase.service;

import java.util.List;

import com.example.springbase.dto.WorkspaceDTO;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IService;

public interface WorkspaceService extends IService<Workspace, String> {
    Workspace createWorkspace (WorkspaceDTO dto);
    List<WorkspaceDTO> getAllWorkspaces(); // Thêm phương thức lấy tất cả workspaces
    Workspace updateWorkspace(String id, WorkspaceDTO dto);
    Boolean deleteWorkspace(String id);
} 
