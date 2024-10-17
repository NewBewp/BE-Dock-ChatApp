package com.example.springbase.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbase.dto.WorkspaceDTO;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.WorkspaceMapper;
import com.example.springbase.repository.WorkspaceRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.WorkspaceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkspaceServiceImpl extends AbstractService<Workspace, String> implements WorkspaceService{

    WorkspaceRepository workspaceRepository;
    WorkspaceMapper workspaceMapper;
    

    @Override
    protected IRepository<Workspace, String> getRepository() {
        return workspaceRepository;
    }

    @Override // Tạo workspace
    public Workspace createWorkspace(WorkspaceDTO dto) {
        Workspace workspace = workspaceMapper.toWorkspace(dto);

        return save(workspace);
    }

    @Override // Lấy tất cả workspaces
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceRepository.findAll().stream()
            .map(workspaceMapper::toDTO) // Chuyển đổi Workspace thành WorkspaceDTO
            .collect(Collectors.toList());
    }

    @Override // Cập nhật workspace
    public Workspace updateWorkspace(String id, WorkspaceDTO dto) {
        Workspace workspace = findOne(id); // Tìm workspace theo id
        workspace.setName(dto.getName());
        workspace.setDescription(dto.getDescription());
        return save(workspace);
    }

    @Override // Xóa mềm workspace
    public Boolean deleteWorkspace(String id) {
        return delete(id); // Sử dụng phương thức delete từ AbstractService
    }
    

}
