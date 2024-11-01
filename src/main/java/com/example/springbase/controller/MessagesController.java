package com.example.springbase.controller;

import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.response.MessageResponse;
import com.example.springbase.entity.Message;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;
import com.example.springbase.model.RequestResponse;
import com.example.springbase.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessagesController extends GenericController<Message, String> {
    @Autowired
    private MessageService messageService;

    @Override
    public IService<Message, String> getService() {
        return messageService;
    }

    @PostMapping("/send") // Đường dẫn để gửi tin nhắn
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest request) {
        MessageResponse response = messageService.sendMessage(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RequestResponse("Send message successful", response));
    }
    
    
}
