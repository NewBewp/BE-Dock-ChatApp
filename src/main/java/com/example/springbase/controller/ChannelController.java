package com.example.springbase.controller;

import com.example.springbase.dto.request.ChannelCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.service.ChannelService;
import com.example.springbase.service.MessageService;
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

    @Override
    public IService<Channel, String> getService() {
        return channelService;
    }

    @PostMapping("/workspace/{workspaceId}") // Endpoint để tạo channel cho workspace
    public ResponseEntity<ChannelResponse> createChannelInSpecificWorkspace(@PathVariable String workspaceId, @RequestBody ChannelCreateRequest request) {
        Channel channel = channelService.createChannelInWorkspace(workspaceId, request); // Tạo channel
        ChannelResponse channelResponse = new ChannelResponse(
                channel.getName(),
                channel.getDescription(),
                channel.getIs_private(),
                workspaceId); // Tạo response cho channel
        return ResponseEntity.status(HttpStatus.CREATED).body(channelResponse); // Trả về response
    }
}
