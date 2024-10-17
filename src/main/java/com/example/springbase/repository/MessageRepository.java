package com.example.springbase.repository;

import org.springframework.stereotype.Repository;

import com.example.springbase.entity.Message;

import com.example.springbase.generic.IRepository;

@Repository
public interface MessageRepository extends IRepository<Message, String> {

}
