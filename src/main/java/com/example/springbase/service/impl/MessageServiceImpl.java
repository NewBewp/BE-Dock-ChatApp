package com.example.springbase.service.impl;

import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.request.MessageUpdateRequest;
import com.example.springbase.dto.response.MessageResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Message;
import com.example.springbase.entity.User;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.MessageMapper;
import com.example.springbase.repository.ChannelRepository;
import com.example.springbase.repository.MessageRepository;
import com.example.springbase.repository.UserRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.MessageService;
import com.example.springbase.util.MessageType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl extends AbstractService<Message, String> implements MessageService {

    MessageRepository messageRepository;
    UserRepository userRepository;
    ChannelRepository channelRepository;
    MessageMapper messageMapper;

    @Override
    protected IRepository<Message, String> getRepository() {
        return messageRepository; // Trả về repository
    }

    @Override
    public MessageResponse sendMessage(MessageRequest request, String senderEmail, String receiverEmail, String channelId) {
//        User sender = userRepository.findByEmail(senderEmail)
//                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
//        if (request.isReply() == false) {
//            if (request.isPrivate() == false) {
//                Channel channel = channelRepository.findById(channelId)
//                        .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Channel not found"));
//                Message message = new Message();
//                message.setContent(request.getContent());
//                message.setMessageType(request.getMessageType());
//                message.setSender(sender);
//                message.setReceiver(null);
//                message.setChannel(channel);
//                if (request.getMessageType() == MessageType.FILE) {
//                    message.setFileUrl(request.getFileUrl());
//                }else {
//                    message.setFileUrl(null);
//                }
//                message.setPrivate(false);
//                message.setReply(false);
//                message.setReplyId(null);
//                message.setIsDeleted(false);
//                messageRepository.save(message);
//            }else {
//                User receiver = userRepository.findByEmail(receiverEmail)
//                        .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
//                Message message = new Message();
//                message.setContent(request.getContent());
//                message.setMessageType(request.getMessageType());
//                message.setSender(sender);
//                message.setReceiver(receiver);
//                message.setChannel(null);
//                if (request.getMessageType() == MessageType.FILE) {
//                    message.setFileUrl(request.getFileUrl());
//                }else {
//                    message.setFileUrl(null);
//                }
//                message.setPrivate(true);
//                message.setReply(false);
//                message.setReplyId(null);
//                message.setIsDeleted(false);
//                messageRepository.save(message);
//            }
//            return m
//        }else
            return null;
    }


    @Override
    public MessageResponse updateMessage(MessageUpdateRequest request, String senderEmail) {
        return null;
    }

}
