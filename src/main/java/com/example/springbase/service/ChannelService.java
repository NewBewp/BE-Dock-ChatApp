package com.example.springbase.service;

import com.example.springbase.dto.request.ChannelCreateRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.generic.IService;

public interface ChannelService extends IService<Channel, String> {
    Channel createChannelInWorkspace(String workspaceId, ChannelCreateRequest request);
    ChannelResponse getDetailChannelInWorkspace(String workspaceId, String channelId);
    // WorkspaceResponse getAllChannelByWorkspaceId(String workspaceId);
    // Channel updatChannel(String id, ChannelRequest request);
    // Boolean deleteChannel(String id);

}
