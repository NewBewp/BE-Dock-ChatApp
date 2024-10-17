package com.example.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/channel")
public class ChannelController extends GenericController<Channel, String> {

    @Autowired
    ChannelService channelService;

    @Autowired 
    MessageService messageService;

    @PostMapping
    @PreAuthorize(AuthConstants.NONE)
    @Transactional
    public ResponseEntity<Channel> createChannelInWorkspace(@RequestBody ChannelRequest request) {
        log.info("ChannelController: {}",request);
        Channel channel = channelService.createChannelInWorkspace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(channel);
    }

    @Override
    public IService<Channel, String> getService() {
        return channelService;
    }
}
