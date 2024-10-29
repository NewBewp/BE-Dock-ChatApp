package com.example.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbase.entity.Message;
import com.example.springbase.generic.GenericController;
import com.example.springbase.generic.IService;

import com.example.springbase.service.MessageService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    
    
}
