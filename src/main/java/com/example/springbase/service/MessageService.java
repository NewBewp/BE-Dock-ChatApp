package com.example.springbase.service;


import com.example.springbase.dto.request.MessageRequest;
import com.example.springbase.dto.request.MessageUpdateRequest;
import com.example.springbase.dto.response.MessageResponse;
import com.example.springbase.entity.Message;
import com.example.springbase.generic.IService;

import java.util.List;

public interface MessageService extends IService<Message, String>{
    MessageResponse sendMessage (MessageRequest request);
    MessageResponse updateMessage (MessageUpdateRequest request, String senderEmail);
    List<MessageResponse> getMessagesByChannelId(String channelId);
    List<MessageResponse> getPrivateMessages(String senderEmail, String receiverEmail);
}
