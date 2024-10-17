package com.example.springbase.service;

import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.generic.IService;

public interface ChannelService extends IService<Channel, String>{
    Channel createChannelInWorkspace (ChannelRequest request);
    ChannelResponse getChannelById(String id);
    Channel updatChannel(String id, ChannelRequest request);
    Boolean deleteChannel(String id);
}
