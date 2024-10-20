package com.example.springbase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.service.WorkspaceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class WorkspaceController extends GenericController<Workspace, String> {
    WorkspaceService workspaceService;

    @Override
    public IService<Workspace, String> getService() {
        return workspaceService;
    }

    @PostMapping()
    public ResponseEntity<Workspace> createWorkspace(@RequestBody WorkspaceRequest dto) {
        log.info("WorkspaceController: {}", dto);
        Workspace workspace = workspaceService.createWorkspace(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspace);
    }

    @PutMapping("/{id}") // Cập nhật workspace
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable String id, @RequestBody WorkspaceRequest dto) {
        Workspace updatedWorkspace = workspaceService.updateWorkspace(id, dto);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(updatedWorkspace);
    }
}