package com.example.springbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Message;
import com.example.springbase.entity.User;
import com.example.springbase.generic.IRepository;
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

    @Override
    protected IRepository<Message, String> getRepository() {
        return messageRepository; // Trả về repository
    }

}
