package com.example.springbase.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    private String username;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    private String lastName;
    private String firstName;
    private String avatarURL;
}
