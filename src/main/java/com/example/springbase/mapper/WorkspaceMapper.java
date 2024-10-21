package com.example.springbase.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Workspace;

@Component
public class WorkspaceMapper {
    public Workspace toWorkspace (WorkspaceRequest request){
        Workspace workspace = new Workspace();  
        workspace.setName(request.getName());
        workspace.setDescription(request.getDescription());
        workspace.setIsDeleted(false);
        return workspace;
    }

    public WorkspaceRequest toDTO (Workspace workspaces){
        WorkspaceRequest dto = new WorkspaceRequest();
        dto.setName(workspaces.getName());
        dto.setDescription(workspaces.getDescription());
        return dto;
    }

    public WorkspaceResponse toResponse (Workspace entity){
        Set<ChannelResponse> channelResponses = entity.getChannels().stream()
            .map(channel -> new ChannelResponse(
                channel.getName(),
                channel.getDescription(),
                channel.getIs_private(),
                entity.getId()))
            .collect(Collectors.toSet());

        return new WorkspaceResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getIsDeleted(),
            channelResponses
        );
    }

    
}
