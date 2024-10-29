package com.example.springbase.entity;

import com.example.springbase.util.MessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tbl_message")
@Data
public class Message extends EntityDefine {
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "sender_email", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_email")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel; // Channel for group messages

    @Column(nullable = true)
    private String fileUrl; // URL for the file or image sent

    @Column(nullable = false)
    private boolean isGroup; // Thêm trường để xác định tin nhắn nhóm

    @Column(nullable = false)
    private boolean isPrivate;

    @Column(nullable =  false)
    private boolean isReply;

    @Column(nullable = true)
    private String replyId;
}
