package com.netty.connection_pool;

public class TestPool {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            //模拟多线程客户端，提交任务，
            new Thread(()-> {
                try {
                    for (int j = 0; j < 10; j++) {
                       String longMsgBody = j + "中华人民共和国,中华人民共和国,中华人民共和国,中华人民共和国,中华人民共和国" + j;
                        NettyTaskPool.submitTask(longMsgBody);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}