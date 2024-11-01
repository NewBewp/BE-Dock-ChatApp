package com.example.springbase.entity;

import com.example.springbase.util.MessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "tbl_message")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message extends EntityDefine {
    @Column(nullable = false)
    String content;

    @Column(nullable = false)
    MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "sender_email", nullable = false)
    User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_email")
    User receiver;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    Channel channel; // Channel for group messages

    @Column(nullable = true)
    String fileUrl; // URL for the file or image sent

    @Column(nullable = false)
    boolean isPrivate;

    @Column(nullable = false)
    boolean isReply;

    @Column(nullable = true)
    String replyId;
}
