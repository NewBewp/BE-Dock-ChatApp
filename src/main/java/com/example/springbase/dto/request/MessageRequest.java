package com.example.springbase.dto.request;

import com.example.springbase.util.MessageType;

import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageRequest {
    String content;
    MessageType messageType; // Type of the message (e.g., CHAT, JOIN, LEAVE)
    String senderEmail;
    String receiverEmail;
    String channelId;
    String fileUrl;
    boolean isPrivate;
    boolean isReply;
    String replyId;
}
