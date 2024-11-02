package com.example.springbase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springbase.entity.Message;

import com.example.springbase.generic.IRepository;

import java.util.List;

@Repository
public interface MessageRepository extends IRepository<Message, String> {
    List<Message> findByChannelId(String channelId);

    @Query("SELECT m FROM Message m WHERE (m.sender.email = :senderEmail AND m.receiver.email = :receiverEmail) OR (m.sender.email = :receiverEmail AND m.receiver.email = :senderEmail)")
    List<Message> findPrivateMessages(@Param("senderEmail") String senderEmail, @Param("receiverEmail") String receiverEmail);
}
