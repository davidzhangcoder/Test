package practise.a2_netty.b2_nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01_20210503 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("./NIOFileChannel01_test.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put("hello, FileChannel".getBytes() );
        byteBuffer.flip();

//        channel.read(byteBuffer);
        channel.write(byteBuffer);

        fileOutputStream.close();
    }

}
