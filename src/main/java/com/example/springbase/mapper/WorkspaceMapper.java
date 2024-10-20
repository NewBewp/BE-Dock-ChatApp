package com.example.springbase.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.entity.Workspace;

@Component
public class WorkspaceMapper {
    public Workspace toWorkspace (WorkspaceRequest dto){
        Workspace workspace = new Workspace();  
        workspace.setName(dto.getName());
        workspace.setDescription(dto.getDescription());
        workspace.setIsDeleted(false);
        return workspace;
    }

    public WorkspaceRequest toDTO (Workspace workspaces){
        WorkspaceRequest dto = new WorkspaceRequest();
        dto.setName(workspaces.getName());
        dto.setDescription(workspaces.getDescription());
        return dto;
    }

    
}
