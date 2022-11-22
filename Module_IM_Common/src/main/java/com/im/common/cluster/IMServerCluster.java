package com.im.common.cluster;

import com.im.common.configuration.Constants;
import com.im.common.configuration.IMServerNode;
import com.im.common.configuration.PropertiesLoader;
import com.im.common.configuration.ZooKeeperClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class IMServerCluster {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMServerCluster.class);

    protected static IMServerCluster instance = new IMServerCluster();

    private final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(3,3,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());

    private final List<IMServerNode> IM_SERVER_LIST = new CopyOnWriteArrayList<IMServerNode>();

    private final AtomicInteger count = new AtomicInteger();

    private final CuratorFramework zooKeeperClient;

    public static IMServerCluster getInstance(){
        return instance;
    }

    protected IMServerCluster(){
        String connectionString = PropertiesLoader.getZooKeeperServers().get(0);
        zooKeeperClient = ZooKeeperClientFactory.createSimple(connectionString);
        zooKeeperClient.start();
    }

    public void init() {
        String path = Constants.REGISTER_ROOT;
        PathChildrenCache childrenCache = new PathChildrenCache(zooKeeperClient, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener(){
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                //LOGGER.debug("开始监听其他的IMServer子节点:-----");
                ChildData data = event.getData();
                switch (event.getType()) {
                    case CHILD_ADDED:
                        LOGGER.info("CHILD_ADDED : " + data.getPath() + "  数据:" + new String(data.getData()));
                        doAfterChildNodeAdded(data);
                        break;
                    case CHILD_REMOVED:
                        LOGGER.info("CHILD_REMOVED : " + data.getPath() + "  数据:" + new String(data.getData()));
                        doAfterChildNodeRemoved(data);
                        break;
                    case CHILD_UPDATED:
                        LOGGER.info("CHILD_UPDATED : " + data.getPath() + "  数据:" + new String(data.getData()));
                        break;
                    default:
                        LOGGER.info("[PathChildrenCache]节点数据为空, path={}, type = {} ", data == null ? "null" : data.getPath() , event.getType() );
                        break;
                }

            }
        };

        childrenCache.getListenable().addListener(pathChildrenCacheListener,THREAD_POOL_EXECUTOR);
        LOGGER.info("Register zk watcher successfully!");
        try {
            childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        } catch (Exception e) {
            LOGGER.error("ZooKeeper 监听异常 : {}" , e.getMessage());
            throw new RuntimeException("ZooKeeper 监听异常");
        }
    }

    protected void doAfterChildNodeAdded(ChildData data) throws Exception {
        String[] address = new String(data.getData()).split(":");
        String host = address[0];
        int port = Integer.parseInt(address[1]);
        IMServerNode imServerNode = new IMServerNode(host, port);
        if(!IM_SERVER_LIST.contains(imServerNode))
            IM_SERVER_LIST.add(imServerNode);
    }

    protected void doAfterChildNodeRemoved(ChildData data){
        String[] address = new String(data.getData()).split(":");
        String host = address[0];
        int port = Integer.parseInt(address[1]);
        IM_SERVER_LIST.remove(new IMServerNode(host, port));
    }

    protected void doAfterChildNodeUpdated(){

    }

    public List<IMServerNode> getAllIMServers() {
        return IM_SERVER_LIST;
    }

    public IMServerNode getAvailableLoadBalencedIMServer() {
        int i = count.getAndIncrement();
        return IM_SERVER_LIST.get(i%IM_SERVER_LIST.size());
    }
}
