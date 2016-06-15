package com.tzz.netty.server;

import java.util.HashMap;
import java.util.Map;

import com.tzz.netty.bean.InformationPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MsgHandleService {
	
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public static Map<String, ChannelHandlerContext> userMap = new HashMap<String, ChannelHandlerContext>();
	
	public static Map<String,InformationPacket.Group.User> userList = new HashMap<String,InformationPacket.Group.User>();

}
