package com.example.springbase.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkspaceDetailResponse {
    String id;
    String name;
    String description;
    Boolean isDeleted;
    List<ChannelDetailResponse> channels;
}
