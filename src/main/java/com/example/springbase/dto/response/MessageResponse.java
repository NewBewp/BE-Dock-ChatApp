package com.example.springbase.dto.response;

import com.example.springbase.util.MessageType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    String content;
    MessageType messageType; // Type of the message (e.g., CHAT, JOIN, LEAVE)
    String senderEmail;
    String receiverEmail;
    String channelId;
    String fileUrl;
    boolean isGroup;
    boolean isPrivate;
    boolean isReply;
    String replyId;
}
