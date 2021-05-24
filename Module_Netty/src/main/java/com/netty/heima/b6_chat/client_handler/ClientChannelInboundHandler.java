package com.netty.heima.b6_chat.client_handler;

import com.netty.heima.b6_chat.message.ChatRequestMessage;
import com.netty.heima.b6_chat.message.LoginRequestMessage;
import com.netty.heima.b6_chat.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ClientChannelInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientChannelInboundHandler.class);

    private Object waiter = new Object();

    private ClientChannelInboundProcessor processor = new ClientChannelInboundProcessor(waiter);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("Channel 已激活");

        new Thread(()->{
            Scanner scan = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String username = scan.nextLine();
            System.out.println("请输入密码：");
            String password = scan.nextLine();
            Message message = new LoginRequestMessage(username, password);
            ctx.writeAndFlush(message);

            try {
                synchronized (waiter) {
                    waiter.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("客户端异常 - {}", e.getMessage());
            }

            while( true ) {
                System.out.println("========");
                System.out.println("menu:");
                System.out.println("send [username] [content]");
                System.out.println("========");

                String command = scan.nextLine();

                String[] commandArray = command.split(" ");
                switch( commandArray[0] ){
                    case "send":
                        ChatRequestMessage chatRequestMessage = new ChatRequestMessage(username,commandArray[1],commandArray[2]);
                        ctx.writeAndFlush(chatRequestMessage);
                        break;
                }
            }

        },"Client_Input_Thread").start();;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        processor.process(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("Read已完成");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("连接已断开");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.debug("异常发生");
        ctx.channel().close();
    }
}
