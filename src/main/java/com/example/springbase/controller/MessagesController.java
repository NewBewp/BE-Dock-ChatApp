package com.example.springbase.controller;

import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.request.MessageUpdateRequest;
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

import java.util.List;


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

    @GetMapping("/channel/{channelId}") // Đường dẫn để lấy tất cả tin nhắn trong kênh
    public ResponseEntity<List<MessageResponse>> getMessagesByChannelId(@PathVariable String channelId) {
        List<MessageResponse> responses = messageService.getMessagesByChannelId(channelId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/private") // Đường dẫn để lấy tất cả tin nhắn riêng tư giữa hai người dùng
    public ResponseEntity<List<MessageResponse>> getPrivateMessages(@RequestParam String senderEmail, @RequestParam String receiverEmail) {
        List<MessageResponse> responses = messageService.getPrivateMessages(senderEmail, receiverEmail);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update") // Đường dẫn để sửa tin nhắn
    public ResponseEntity<MessageResponse> updateMessage(@RequestBody MessageUpdateRequest request, @RequestParam String senderEmail) {
        MessageResponse response = messageService.updateMessage(request, senderEmail);
        return ResponseEntity.ok(response);
    }
}
