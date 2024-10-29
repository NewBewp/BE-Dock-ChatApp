package com.example.springbase.dto.request;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageUpdateRequest {
    String content; 
    String senderEmail;
    String receiverEmail;
    String channelId;
    String fileUrl;
    boolean isGroup;
    boolean isPrivate;
    boolean isReply;
    String replyId;
}
