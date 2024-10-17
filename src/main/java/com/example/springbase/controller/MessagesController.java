package com.example.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbase.entity.Message;
import com.example.springbase.record.MessageRecord;
import com.example.springbase.service.MessageService;
import com.example.springbase.service.impl.MessageServiceImpl;
import com.example.springbase.util.AuthConstants;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessagesController {
    @Autowired
    private MessageService messageService;

    @PostMapping// Đường dẫn cho API tạo tin
    @PreAuthorize(AuthConstants.NONE)
    @Transactional
    public ResponseEntity<Message> createMessage(@RequestBody MessageRecord record) {
        Message createdMessage = messageService.createMessage(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage); // Trả về tin nhắn đã tạo
    }
}
