package com.example.springbase.controller;

import com.example.springbase.dto.request.WorkspaceRequest;
import com.example.springbase.dto.response.WorkspaceDetailResponse;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.User;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.model.RequestResponse;
import com.example.springbase.service.WorkspaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class WorkspaceController extends GenericController<Workspace, String> {
    @Autowired
    WorkspaceService workspaceService;

    @Override
    public IService<Workspace, String> getService() {
        return workspaceService;
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<?> createWokrspaceByUser(@PathVariable String userId,@RequestBody WorkspaceRequest request) {
        WorkspaceResponse workspace = workspaceService.createWorkspaceByUser(userId,request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RequestResponse("Create Workspace successful", workspace));
    }

    @PutMapping("/{id}") // Cập nhật workspace
    public ResponseEntity<?> updateWorkspace(@PathVariable String id,
            @RequestBody WorkspaceRequest request) {
        WorkspaceResponse updatedWorkspace = workspaceService.updateWorkspace(id, request);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED)
                .body(new RequestResponse("Update Workspace successful", updatedWorkspace));
    }

    @GetMapping("/find/{id}") // Lấy workspace theo ID
    public ResponseEntity<?> getWorkspaceById(@PathVariable String id) {
        WorkspaceResponse workspaceResponse = workspaceService.findWorkspaceById(id);
        if (workspaceResponse != null) {
            return ResponseEntity.ok(workspaceResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Trả về 404 nếu không tìm thấy
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getWorkspaceByUserId(@PathVariable String userId){
        Set<WorkspaceResponse> workspaces = workspaceService.findWorkspaceByUserId(userId);
        return ResponseEntity.ok(workspaces);
    }

    // @GetMapping("/entity/{id}")
    // public ResponseEntity<?> findWorkspaceEntityById (@PathVariable String id){
    // WorkspaceDetailResponse workspaceDetail =
    // workspaceService.findWorkspaceEntityById(id);
    // return ResponseEntity.ok(workspaceDetail);
    // }

    // @PostMapping
    // public ResponseEntity<?> createWorkspaceByUser(@RequestBody WorkspaceRequest
    // request,
    // @AuthenticationPrincipal User user) {
    // WorkspaceResponse workspace = workspaceService.createWorkspaceByUser(request,
    // user);
    // return ResponseEntity.status(HttpStatus.CREATED)
    // .body(new RequestResponse("Create Workspace successful", workspace));
    // }
}