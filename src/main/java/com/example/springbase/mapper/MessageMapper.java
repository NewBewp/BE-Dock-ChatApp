package com.example.springbase.mapper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.response.MessageResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Message;
import com.example.springbase.entity.User;
import com.example.springbase.repository.ChannelRepository;
import com.example.springbase.repository.MessageRepository;
import com.example.springbase.repository.UserRepository;

@Component
public class MessageMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;

    public Message toEntity(MessageRequest request) {
        Message message = new Message();
        message.setContent(request.getContent());
        message.setMessageType(request.getMessageType());
        message.setFileUrl(request.getFileUrl());
        message.setGroup(request.isGroup());
        message.setPrivate(request.isPrivate());

        User sender = userRepository.findByEmail(request.getSenderEmail()).orElse(null);
        message.setSender(sender);

        message.setReceiver(request.getReceiverEmail() != null ? 
            userRepository.findByEmail(request.getReceiverEmail()).orElse(null) : null);
    
        message.setChannel(request.getChannelId() != null ?
            channelRepository.findById(request.getChannelId()).orElse(null) : null);

        return message;
    }

    public MessageResponse toResponse(Message message) {
        MessageResponse response = new MessageResponse();
        response.setContent(message.getContent());
        response.setMessageType(message.getMessageType());
        response.setFileUrl(message.getFileUrl());
        response.setGroup(message.isGroup());
        response.setPrivate(message.isPrivate());
        response.setSenderEmail(message.getSender() != null ? message.getSender().getEmail() : null);
        response.setReceiverEmail(message.getReceiver() != null ? message.getReceiver().getEmail() : null);
        response.setChannelId(message.getChannel() != null ? message.getChannel().getId() : null);
        return response;
    }
}
