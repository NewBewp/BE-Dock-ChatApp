package com.example.springbase.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.repository.WorkspaceRepository;

@Component
public class ChannelMapper {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    public Channel toChannel (ChannelRequest request){
        Channel channel = new Channel();
        channel.setName( request.getName());
        channel.setDescription(request.getDescription());
        channel.setIs_private(request.getIs_private());
        channel.setIsDeleted(false);
        
        Workspace workspace = workspaceRepository.findById(request.getWorkspace_id())
        .orElseThrow(()-> new RuntimeException("Workspace not found"));

        channel.setWorkspaces(workspace);

        return channel;
    }
}
