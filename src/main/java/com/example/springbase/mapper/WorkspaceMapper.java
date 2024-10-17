package com.example.springbase.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.springbase.dto.WorkspaceDTO;
import com.example.springbase.entity.Workspace;

@Component
public class WorkspaceMapper {
    public Workspace toWorkspace (WorkspaceDTO dto){
        Workspace workspace = new Workspace();  
        workspace.setName(dto.getName());
        workspace.setDescription(dto.getDescription());
        workspace.setIsDeleted(false);
        return workspace;
    }

    public WorkspaceDTO toDTO (Workspace workspaces){
        WorkspaceDTO dto = new WorkspaceDTO();
        dto.setName(workspaces.getName());
        dto.setDescription(workspaces.getDescription());
        return dto;
    }

    
}
