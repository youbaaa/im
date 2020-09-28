/*
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.youbaaa.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author huang.zhangh
 * @version IMServer.java, v 0.1 2020-09-28 14:34
 */
public class IMServer {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast();
                    }
                });
        bind(bootstrap);
    }

    private static void bind(ServerBootstrap bootstrap) {
        bootstrap.bind(IMServer.PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "««««IM服务端绑定端口[" + PORT + "]成功!");
            } else {
                System.err.println(new Date() + "««««IM服务端绑定端口[" + PORT + "]失败!");
            }
        });
    }
}