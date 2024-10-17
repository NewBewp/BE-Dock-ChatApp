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
    private MessageRepository messageRepo;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private ChannelRepository channelRepository;



    @Override
    public Message createMessage(MessageRecord record) {

        Message message = new Message();
        message.setContent(record.content());
        message.setMessageType(record.messageType());
        // message.setGroup(record.isGroup());
        message.setGroup(false);
        message.setIsDeleted(false);

        // Fetch and set the sender, receiver, and channel
        // User sender = userRepository.findById(record.sender())
        //         .orElseThrow(() -> new IllegalArgumentException("Invalid sender ID"));
        // message.setSender(sender);
    
        // User receiver = userRepository.findById(record.receiver())
        //         .orElseThrow(() -> new IllegalArgumentException("Invalid receiver ID"));
        // message.setReceiver(receiver);
    
        // Channel channel = channelRepository.findById(record.channel())
        //         .orElseThrow(() -> new IllegalArgumentException("Invalid channel ID"));
        // message.setChannel(channel);
    
        // Save the message using the repository
        return messageRepo.save(message);

    }

    @Override
    protected IRepository<Message, String> getRepository() {
        return messageRepo; // Trả về repository
    }





}
