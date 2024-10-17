package com.example.springbase.record;

import com.example.springbase.util.MessageType;


public record MessageRecord(
        String content,
        MessageType messageType
        // String sender,
        // String receiver,
        // Boolean isGroup
        // String channel

) {
    // public MessageRecord {
    //     if (isGroup == null) {
    //         isGroup = false;
    //     }
    // }

}
