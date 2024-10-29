package com.example.springbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.request.MessageUpdateRequest;
import com.example.springbase.dto.response.MessageResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Message;
import com.example.springbase.entity.User;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.MessageMapper;
import com.example.springbase.record.MessageRecord;
import com.example.springbase.repository.ChannelRepository;
import com.example.springbase.repository.MessageRepository;
import com.example.springbase.repository.UserRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageServiceImpl extends AbstractService<Message, String> implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    protected IRepository<Message, String> getRepository() {
        return messageRepository; // Trả về repository
    }

    @Override 
    public MessageResponse createMessage (MessageRequest request){       
        
        Message message = messageMapper.toEntity(request);
        return messageMapper.toResponse(save(message));
    }

    @Override
    public MessageResponse updateMessage(MessageUpdateRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMessage'");
    }   


}
