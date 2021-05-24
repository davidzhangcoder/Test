package practise.a2_netty.b2_nio.b_20200430_not_use_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        List<SocketChannel> listSocketChannel = new ArrayList<SocketChannel>();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        while(true) {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
            SocketChannel clientSockedChannel = serverSocketChannel.accept(); //不阻塞

            if( clientSockedChannel == null ) {
                System.out.println("clientSockedChannel is null");
            } else {
                listSocketChannel.add(clientSockedChannel);
                clientSockedChannel.configureBlocking(false);
            }

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //效率低，资源浪费，因为每次都要循环遍历
            for (SocketChannel socketChannel : listSocketChannel) {
                int read = socketChannel.read(buffer); //不阻塞
                if (read > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);

                    InetSocketAddress inetSocketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
                    System.out.println( "from client: " + inetSocketAddress.getAddress().getHostAddress() + ":"+inetSocketAddress.getPort() + " : " + new String(bytes));

                    //切换到写模式
                    buffer.clear();
                }
            }
        }
    }

}
