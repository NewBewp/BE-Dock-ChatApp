package com.example.springbase.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChannelDetailResponse {
    String id;
    String name; 
    String description;
    Boolean isPrivate;
    Boolean isDeleted;
    String workspace;
}