package com.example.springbase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springbase.dto.request.ChannelCreateRequest;
import com.example.springbase.dto.request.ChannelRequest;
import com.example.springbase.dto.response.ChannelResponse;
import com.example.springbase.entity.Channel;
import com.example.springbase.entity.Workspace;
import com.example.springbase.exception.ErrorHandler;
import com.example.springbase.generic.IRepository;
import com.example.springbase.mapper.ChannelMapper;
import com.example.springbase.repository.ChannelRepository;
import com.example.springbase.repository.WorkspaceRepository;
import com.example.springbase.service.AbstractService;
import com.example.springbase.service.ChannelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChannelServiceImpl extends AbstractService<Channel, String> implements ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    WorkspaceRepository workspaceRepository;



    @Override
    protected IRepository<Channel, String> getRepository() {
        return channelRepository;
    }

    @Override
    public Channel createChannelInWorkspace(String workspaceId, ChannelCreateRequest request) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new ErrorHandler(HttpStatus.NOT_FOUND, "Workspace not found"));

        Channel channel = channelMapper.toChannel(request);
        channel.setWorkspace(workspace);
        Channel savedChannel = channelRepository.save(channel);
        // log.info("Saved Channel >>> {}", savedChannel) ;
        return savedChannel;
    }

    @Override
    public ChannelResponse getChannelById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChannelById'");
    }

    @Override
    public Channel updatChannel(String id, ChannelRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatChannel'");
    }

    @Override
    public Boolean deleteChannel(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteChannel'");
    }

}
