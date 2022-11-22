package com.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

public class TestZooKeeper {

    private static final String ZK_ADDRESS = "192.168.1.6:2181";

    public static void main(String[] args) {
        createNode();
//        createEphemeralNode();
    }

    public static void createNode() {
        //创建客户端
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        try {//启动客户端实例,连接服务器 client.start();
        // 创建一个ZNode节点
        // 节点的数据为 payload
            String data = "hello";
            byte[] payload = data.getBytes("UTF-8");
            String zkPath = "/test2/CRUD/node-2";

            client.start();
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zkPath, payload);

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }

    public static void createEphemeralNode() {
        //创建客户端
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        try {//启动客户端实例,连接服务器 client.start();
        // 创建一个ZNode节点
        // 节点的数据为 payload
            String data = "hello";
            byte[] payload = data.getBytes("UTF-8");
            String zkPath = "/testEphemeral";

            client.start();
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(zkPath, payload);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }


}
