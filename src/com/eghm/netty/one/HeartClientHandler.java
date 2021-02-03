package com.eghm.netty.one;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author 殿小二
 * @date 2021/2/3
 */
public class HeartClientHandler extends SimpleChannelInboundHandler<String> {

    private static final String PONG = "PONG";

    private HeartClient heartClient;

    public HeartClientHandler(HeartClient heartClient) {
        this.heartClient = heartClient;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (PONG.equals(msg)) {
            System.out.println("收到响应PONG");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent)evt).state();
            if(state == IdleState.ALL_IDLE) {
                ctx.writeAndFlush("PING");
                System.out.println("读写超时,开始发送PING");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接... 尝试重连");
        ctx.channel().eventLoop().schedule(() -> {
            heartClient.connect();
        }, 5, TimeUnit.SECONDS);
    }
}
