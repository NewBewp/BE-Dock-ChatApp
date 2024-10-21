package com.example.springbase.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.WorkspaceMapper;
import com.example.springbase.repository.ChannelRepository;
import com.example.springbase.repository.WorkspaceRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.ChannelService;
import com.example.springbase.service.WorkspaceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkspaceServiceImpl extends AbstractService<Workspace, String> implements WorkspaceService {

    WorkspaceRepository workspaceRepository;
    WorkspaceMapper workspaceMapper;
    ChannelService channelService;

    @Override
    protected IRepository<Workspace, String> getRepository() {
        return workspaceRepository;
    }

    @Override // Tạo workspace
    public WorkspaceResponse createWorkspace(WorkspaceRequest request) {
        Workspace workspace = workspaceMapper.toWorkspace(request);
        Workspace savedWorkspace = save(workspace); // Save the workspace first

        // Create a channel for the newly created workspace
        ChannelRequest channelRequest = new ChannelRequest();
        channelRequest.setName("general"); // Set the default name for the channel
        channelRequest.setDescription("description general"); // Set the default description for the channel
        channelRequest.setIs_private(false);
        channelRequest.setWorkspace_id(savedWorkspace.getId()); // Set the workspace ID

        // Create the channel using the ChannelService
        Channel createdChannel = channelService.createChannelInWorkspace(channelRequest); // Create the channel

        // Add the created channel to the workspace's channels set
        savedWorkspace.getChannels().add(createdChannel); // Maintain the relationship

        return workspaceMapper.toResponse(savedWorkspace); // Return the saved workspace

    }

    @Override // Lấy tất cả workspaces
    public List<WorkspaceResponse> getAllWorkspaces() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toResponse) // Chuyển đổi Workspace thành WorkspaceDTO
                .collect(Collectors.toList());
    }

    @Override // Cập nhật workspace
    public WorkspaceResponse updateWorkspace(String id, WorkspaceRequest requet) {
        Workspace workspace = findOne(id); // Tìm workspace theo id
        if (workspace == null) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "Workspace not found");
        }
        workspace.setName(requet.getName());
        workspace.setDescription(requet.getDescription());
        return workspaceMapper.toResponse(save(workspace));
    }

    @Override // Xóa mềm workspace
    public Boolean deleteWorkspace(String id) {
        return delete(id); // Sử dụng phương thức delete từ AbstractService
    }
    
    @Override // Tìm workspace theo ID
    public WorkspaceResponse findWorkspaceById(String id) {
        Workspace workspace = findOne(id); // Use the inherited method to find the workspace
        if (workspace == null) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "Workspace not found");
        }
        return workspaceMapper.toResponse(workspace); // Convert to WorkspaceResponse
    }
}
