package com.example.springbase.controller;

import com.example.springbase.dto.request.ChannelCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.dto.response.WorkspaceResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.model.RequestResponse;
import com.example.springbase.service.ChannelService;
import com.example.springbase.service.MessageService;
import com.example.springbase.service.WorkspaceService;
import com.example.springbase.util.AuthConstants;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/channels")
public class ChannelController extends GenericController<Channel, String> {

    @Autowired
    ChannelService channelService;

    @Autowired
    MessageService messageService;

    @Autowired
    WorkspaceService workspaceService;

    @Override
    public IService<Channel, String> getService() {
        return channelService;
    }

    @PostMapping("/workspace/{workspaceId}/create-channels") // Endpoint để tạo channel cho workspace
    public ResponseEntity<?> createChannelInWorkspace(@PathVariable String workspaceId, @RequestBody ChannelCreateRequest request) {
        Channel channel = channelService.createChannelInWorkspace(workspaceId, request); // Tạo channel
        ChannelResponse channelResponse = new ChannelResponse(
                channel.getId(),
                channel.getName(),
                channel.getDescription(),
                channel.getIs_private(),
                workspaceId); // Tạo response cho channel
        return ResponseEntity.status(HttpStatus.CREATED).body(new RequestResponse("Create Channel successful", channelResponse)); // Trả về response
    }

    @GetMapping("/workspace/{workspaceId}/channels")
    public ResponseEntity<?> getAllChannelInWorkspace(@PathVariable String workspaceId){
        WorkspaceResponse workspace = workspaceService.findWorkspaceById(workspaceId);
        return ResponseEntity.ok(workspace);
    }

    @GetMapping("/workspace/{workspaceId}/channels/{channelId}")
    public ResponseEntity<?> getDetailChannelsInWorkspace(@PathVariable String workspaceId, @PathVariable String channelId){
        ChannelResponse channel = channelService.getDetailChannelInWorkspace(workspaceId, channelId);
        return ResponseEntity.ok(channel);
    }
}
