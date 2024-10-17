package com.example.springbase.repository;

import org.springframework.stereotype.Repository;

import com.example.springbase.entity.Channel;

import com.example.springbase.generic.IRepository;

@Repository
public interface ChannelRepository extends IRepository<Channel, String> {

}
