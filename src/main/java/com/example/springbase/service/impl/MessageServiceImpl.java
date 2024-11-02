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

import java.util.List;
import java.util.stream.Collectors;

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
    public MessageResponse sendMessage(MessageRequest request) {
        User sender = userRepository.findByEmail(request.getSenderEmail())
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
        Message message = new Message();
        message.setContent(request.getContent());
        message.setMessageType(request.getMessageType());
        message.setSender(sender);
        message.setIsDeleted(false);
        if (request.isReply() == false) { // Tin nhắn không phải trả lời
            if (request.isPrivate() == false) {
                Channel channel = channelRepository.findById(request.getChannelId())
                        .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Channel not found"));
                message.setChannel(channel);
                message.setReceiver(null);
            } else {
                User receiver = userRepository.findByEmail(request.getReceiverEmail())
                        .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "User not found"));
                message.setReceiver(receiver);
                message.setChannel(null);
            }

            if (request.getMessageType() == MessageType.FILE) {
                message.setFileUrl(request.getFileUrl());
            } else {
                message.setFileUrl(null);
            }

            message.setPrivate(request.isPrivate());
            message.setReply(false);
            message.setReplyId(null);
            messageRepository.save(message);
        } else {
            // Logic xử lý cho tin nhắn trả lời
            Message originalMessage = messageRepository.findById(request.getReplyId())
                    .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Original message not found"));

            message.setReply(true);
            message.setReplyId(originalMessage.getId());
            message.setChannel(originalMessage.getChannel());
            message.setReceiver(originalMessage.getSender());
            message.setPrivate(originalMessage.isPrivate());

            if (request.getMessageType() == MessageType.FILE) {
                message.setFileUrl(request.getFileUrl());
            } else {
                message.setFileUrl(null);
            }

            messageRepository.save(message);
        }
        return messageMapper.toResponse(message);
    }


    @Override
    public MessageResponse updateMessage(MessageUpdateRequest request, String senderEmail) {
        // Tìm tin nhắn theo ID
        Message message = messageRepository.findById(request.getMessageId())
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Message not found"));

        // Kiểm tra xem người gửi có quyền sửa tin nhắn không
        if (!message.getSender().getEmail().equals(senderEmail)) {
            throw new ErrorHandler(HttpStatus.FORBIDDEN, "You do not have permission to edit this message");
        }

        // Cập nhật nội dung tin nhắn
        message.setContent(request.getContent());
        messageRepository.save(message); // Lưu thay đổi

        return messageMapper.toResponse(message); // Trả về phản hồi
    }

    @Override
    public List<MessageResponse> getMessagesByChannelId(String channelId) {
        List<Message> messages = messageRepository.findByChannelId(channelId); // Giả sử bạn đã định nghĩa phương thức này trong MessageRepository
        return messages.stream()
                .map(messageMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageResponse> getPrivateMessages(String senderEmail, String receiverEmail) {
        List<Message> messages = messageRepository.findPrivateMessages(senderEmail, receiverEmail); // Gọi phương thức từ repository
        return messages.stream()
                .map(messageMapper::toResponse)
                .collect(Collectors.toList());
    }
} 
