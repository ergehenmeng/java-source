package com.eghm.netty.one;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 殿小二
 * @date 2021/2/3
 */
public class HeartServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String PING = "PING";

    private static final int MAX_RETRY = 3;

    private AtomicInteger count = new AtomicInteger();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        if (PING.equals(msg)) {
            System.out.println("接收到客户端PING信号");
            ctx.writeAndFlush("PONG");
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("5秒时间内未接收到 " + ctx.channel().remoteAddress() + " 的信号");
                if (count.getAndIncrement() > MAX_RETRY) {
                    System.out.println("15秒内没有收到" + ctx.channel().remoteAddress() + " 的信号,执行关闭操作");
                    ctx.close();
                }
                return;
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端链接成功");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已断开链接");
    }
}
