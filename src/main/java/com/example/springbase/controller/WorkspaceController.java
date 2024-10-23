package com.example.springbase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.service.WorkspaceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Slf4j
@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class WorkspaceController extends GenericController<Workspace, String> {
    WorkspaceService workspaceService;

    @Override
    public IService<Workspace, String> getService() {
        return workspaceService;
    }

    @PostMapping()
    public ResponseEntity<WorkspaceResponse> createWorkspace(@RequestBody WorkspaceRequest request) {

        WorkspaceResponse workspace = workspaceService.createWorkspace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspace);
    }

    @PutMapping("/{id}") // Cập nhật workspace
    public ResponseEntity<WorkspaceResponse> updateWorkspace(@PathVariable String id,
            @RequestBody WorkspaceRequest request) {
        WorkspaceResponse updatedWorkspace = workspaceService.updateWorkspace(id, request);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(updatedWorkspace);
    }

    @GetMapping("/find/{id}") // Lấy workspace theo ID
    public ResponseEntity<WorkspaceResponse> getWorkspaceById(@PathVariable String id) {
        WorkspaceResponse workspaceResponse = workspaceService.findWorkspaceById(id);
        return ResponseEntity.ok(workspaceResponse); // Return the found workspace
    }
}