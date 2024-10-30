package com.example.springbase.mapper;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbase.dto.request.ChannelCreateRequest;
import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.repository.WorkspaceRepository;

@Component
public class ChannelMapper {
    public Channel toChannel(ChannelCreateRequest request){
        Channel channel = new Channel();
        channel.setName(request.getName());
        channel.setDescription(request.getDescription());
        // channel.setIs_private(request.getIs_private());
        channel.setIs_private(false);
        channel.setIsDeleted(false);
        return channel;
    }

    public ChannelResponse toChannelResponse(Channel channel){
        ChannelResponse channelResponse = new ChannelResponse();
        channelResponse.setId(channel.getId());
        channelResponse.setName(channel.getName());
        channelResponse.setDescription(channel.getDescription());
        channelResponse.setIsPrivate(channel.getIs_private());
        return channelResponse;        
    }
}
