package com.example.springbase.controller;

import com.example.springbase.dto.request.ChannelUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Message;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.record.MessageRecord;
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

    @PostMapping("/workspace/{workspaceId}")
    @PreAuthorize(AuthConstants.NONE)
    @Transactional
    public ResponseEntity<Channel> createChannelInSpecificWorkspace(@PathVariable String workspaceId, @RequestBody ChannelRequest request) {
        request.setWorkspace_id(workspaceId); // Set the workspace ID in the request
        Channel channel = channelService.createChannelInWorkspace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(channel);
    }

    // @PutMapping
    // @PreAuthorize(AuthConstants.NONE)
    // @Transactional
    // public ResponseEntity<Channel> updateChannelInWorkspace(@PathVariable String id,@RequestBody ChannelUpdateRequest request) {
    //     Channel channel = channelService.findOne(id);
    //     return
    // }
}
