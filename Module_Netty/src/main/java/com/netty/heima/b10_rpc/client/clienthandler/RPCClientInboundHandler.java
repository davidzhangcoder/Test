package com.netty.heima.b10_rpc.client.clienthandler;

import com.netty.heima.b10_rpc.client.ResultHolder;
import com.netty.heima.b10_rpc.common.message.RPCResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class RPCClientInboundHandler extends SimpleChannelInboundHandler<RPCResponseMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCClientInboundHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponseMessage msg) throws Exception {
        if (msg.getResult()!=null) {
            LOGGER.debug(msg.getResult().toString());
        }

        long seqID = msg.getSeqID();
        Promise promise = ResultHolder.getInstance().get(seqID);

        if( promise != null ) {
            if (msg.isSuccess()) {
                promise.setSuccess(msg.getResult());
            } else {
                promise.setFailure(new Exception(msg.getMessage()));
            }
        } else {
            //todo
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }
}
