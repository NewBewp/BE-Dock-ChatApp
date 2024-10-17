package com.example.springbase.entity;

import com.example.springbase.util.MessageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "tbl_message")
@Data
public class Message extends EntityDefine {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private MessageType messageType;

    // @ManyToOne
    // @JoinColumn(name = "sender_id")
    // private User sender; // Thêm trường người gửi

    // @ManyToOne
    // @JoinColumn(name = "receiver_id")
    // private User receiver; // Thêm trường người nhận

    // @ManyToOne
    // @JoinColumn(name = "channel_id")
    // private Channel channel; // Thêm trường kênh chat

    @Column(nullable = false)
    private boolean isGroup; // Thêm trường để xác định tin nhắn nhóm
}
