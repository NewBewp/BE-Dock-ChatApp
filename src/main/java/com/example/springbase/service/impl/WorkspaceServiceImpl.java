package com.example.springbase.service.impl;

import com.example.springbase.dto.request.ChannelCreateRequest;
import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.ChannelDetailResponse;
import com.example.springbase.dto.response.WorkspaceDetailResponse;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.WorkspaceMapper;
import com.example.springbase.repository.WorkspaceRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.ChannelService;
import com.example.springbase.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class WorkspaceServiceImpl extends AbstractService<Workspace, String> implements WorkspaceService {
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Autowired
    WorkspaceMapper workspaceMapper;

    @Autowired
    ChannelService channelService;

    @Override
    protected IRepository<Workspace, String> getRepository() {
        return workspaceRepository;
    }

    @Override // Tạo workspace
    public WorkspaceResponse createWorkspace(WorkspaceRequest request) {
        
        Workspace workspace = workspaceMapper.toWorkspace(request);
        workspace.setChannels(new ArrayList<>());
        Workspace savedWorkspace = save(workspace); // Save the workspace first
        // Tạo channel mặc định cho workspace
        ChannelCreateRequest channelRequest = new ChannelCreateRequest();
        channelRequest.setName("General"); // Tên channel mặc định
        channelRequest.setDescription("Default channel for the workspace"); // Mô tả channel
        channelRequest.setIs_private(false);

        Channel channel = channelService.createChannelInWorkspace(savedWorkspace.getId(), channelRequest);
        // Add the created channel to the workspace's channels set
        savedWorkspace.getChannels().add(channel); // Maintain the relationship

        return workspaceMapper.toResponse(savedWorkspace); // Return the saved workspace

    }

    @Override // Lấy tất cả workspaces
    public Set<WorkspaceResponse> getAllWorkspaces() {
        return workspaceRepository.findAll().stream()
                .map(workspaceMapper::toResponse) // Chuyển đổi Workspace thành WorkspaceDTO
                .collect(Collectors.toSet());
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
        Workspace workspace = findOne(id);

        if (workspace == null) {
            throw new ErrorHandler(HttpStatus.NOT_FOUND, "Workspace not found");
        } else {
            return workspaceMapper.toResponse(workspace);
        }
    }

    @Override
    public WorkspaceDetailResponse findWorkspaceEntityById(String id) {
        Workspace workspace = findOne(id);
        List<ChannelDetailResponse> channelResponses = workspace.getChannels().stream()
                .map(channel -> new ChannelDetailResponse(
                        channel.getId(),
                        channel.getName(),
                        channel.getDescription(),
                        channel.getIs_private(),
                        channel.getIsDeleted(),
                        workspace.getId() // ID của workspace
                ))
                .collect(Collectors.toList());
        return new WorkspaceDetailResponse(
                workspace.getId(),
                workspace.getName(),
                workspace.getDescription(),
                workspace.getIsDeleted(),
                channelResponses);
    }
}
