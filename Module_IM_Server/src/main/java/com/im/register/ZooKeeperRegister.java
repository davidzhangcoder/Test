package com.im.register;

import com.im.common.configuration.*;
import com.im.server.IMServerClusterServerSide;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ZooKeeperRegister extends Register{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperRegister.class);

    private CuratorFramework client;

    ZooKeeperRegister() {
        //todo: 如不能注册， 需要尝试下一份地址
        this.client = ZooKeeperClientFactory.createSimple(getRegisterServer().get(0));
        this.client.start();
    }

    public RegisterType getRegisterType() {
        return RegisterType.ZooKeeper;
    }

    public List<String> getRegisterServer(){
        return PropertiesLoader.getZooKeeperServers();
    }

    public void close() {
        CloseableUtils.closeQuietly(client);
    }

    public void register(IMServerNode imServerNode) {
        String zkPath = Constants.REGISTER_ROOT;

        Stat stat = null;
        try {
            stat = client.checkExists().forPath(zkPath);
        } catch (Exception e) {
            LOGGER.error("检查ZooKeeper Node是否存在错误 : {}", e.getMessage());
        }
        if( stat == null )
            createZKNode("", zkPath, CreateMode.PERSISTENT);

        String data = imServerNode.getHost()+ ":" + imServerNode.getPort();
        zkPath = Constants.REGISTER_ROOT + Constants.REGISTER_PATH_PREFIX;
        createZKNode( data, zkPath , CreateMode.EPHEMERAL_SEQUENTIAL);

        IMServerClusterServerSide.getInstance().init();
    }

    private boolean createZKNode(String data, String zkPath, CreateMode createMode) {
        // 创建一个ZNode节点
        try {
            // 节点的数据为 payload
            byte[] payload = data.getBytes("UTF-8");

            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(createMode)
                    .forPath(zkPath, payload);

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

}
