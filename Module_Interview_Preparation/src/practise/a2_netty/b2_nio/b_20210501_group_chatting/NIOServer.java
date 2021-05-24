package practise.a2_netty.b2_nio.b_20210501_group_chatting;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册后的selectionkey 数量=" + selector.keys().size());

        while(true) {
            if(selector.selectNow()>0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> it = selectionKeys.iterator();
                while(it.hasNext()) {
                    SelectionKey selectionKey = it.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(100));
                        System.out.println("客户端连接后 ，注册的selectionkey 数量=" + selector.keys().size());
                    }
                    else if(selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();

                        int read = channel.read(byteBuffer);
                        if(read > 0) {
                            //切换到读模式
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.limit()];
                            byteBuffer.get(bytes);

                            sendToOthers(selector,channel,bytes);

                            InetSocketAddress inetSocketAddress = (InetSocketAddress) channel.getRemoteAddress();
                            System.out.println( "from client: " + inetSocketAddress.getAddress().getHostAddress() + ":"+inetSocketAddress.getPort() + " : " + new String(bytes));

                            //切换到写模式
                            byteBuffer.clear();
                        }
                    }
                    it.remove();
                }
            }
        }
    }

    private static void sendToOthers(Selector selector , SocketChannel source , byte[] msg) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            SelectableChannel channel = key.channel();

            if (channel instanceof SocketChannel && channel!=source) {
                ((SocketChannel)channel).write(ByteBuffer.wrap(msg));
            }
        }
    }

}
