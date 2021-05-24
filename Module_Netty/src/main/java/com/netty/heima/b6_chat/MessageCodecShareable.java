package com.netty.heima.b6_chat;

import com.netty.heima.b6_chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@ChannelHandler.Sharable
public class MessageCodecShareable extends MessageToMessageCodec<ByteBuf, Message> {

    private static final Logger log = LoggerFactory.getLogger(MessageCodecShareable.class);

//    魔数：用来在第一时间判定接收的数据是否为无效数据包
//    版本号：可以支持协议的升级
//    序列化算法：消息正文到底采用哪种序列化反序列化方式
//    如：json、protobuf、hessian、jdk
//    指令类型：是登录、注册、单聊、群聊… 跟业务相关
//    请求序号：为了双工通信，提供异步能力
//    正文长度
//    消息正文


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {

        ByteBuf byteBuf = ctx.alloc().buffer();

        //1.魔数 4个字节
        byteBuf.writeBytes(new byte[]{1,2,3,4});

        //2.版本号 1个字节
        byteBuf.writeByte(1);

        //3.序列化算法 1个字节 - 0:JDK, 1:JSON
        byteBuf.writeByte(0);

        //4.指令类型 1个字节
        byteBuf.writeByte(0);

        //5.请求序号 1个字节
        byteBuf.writeByte(0);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        //6.正文长度 4个字节
        byteBuf.writeInt(bytes.length);

//        byteBuf.writeByte(0xff);
//        byteBuf.writeByte(0xff);
//        byteBuf.writeByte(0xff);
//        byteBuf.writeByte(0xff);

        //7.消息正文
        byteBuf.writeBytes(bytes);

        out.add(byteBuf);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //1.魔数 4个字节
        byte[] magicNumber = new byte[4];
        in.readBytes(magicNumber);

        //2.版本号 1个字节
        byte version = in.readByte();

        //3.序列化算法 1个字节
        byte serializerAlgo = in.readByte();

        //4.指令类型 1个字节
        byte type = in.readByte();

        //5.请求序号 1个字节
        byte seq = in.readByte();

        //6.正文长度 4个字节
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

//        in.readByte();
//        in.readByte();
//        in.readByte();
//        in.readByte();

        ByteArrayInputStream bis = new ByteArrayInputStream(content);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Message message = (Message) ois.readObject();

        //log.debug( message.toString() );

        ctx.fireChannelRead(message);
    }
}
