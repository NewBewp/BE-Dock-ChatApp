package com.example.springbase.service;


import com.example.springbase.entity.Message;
import com.example.springbase.generic.IService;
import com.example.springbase.record.MessageRecord;

public interface MessageService extends IService<Message, String>{
    Message createMessage(MessageRecord record);
}
