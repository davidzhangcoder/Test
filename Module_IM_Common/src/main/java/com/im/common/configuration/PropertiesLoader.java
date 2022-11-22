package com.im.common.configuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesLoader {

    private static Properties props = new Properties();

    private static PropertiesLoader instance;

    static {
        if (instance == null) {
            synchronized (PropertiesLoader.class) {
                if (instance == null) {
                    instance = new PropertiesLoader();
                    instance.init();
                }
            }
        }

    }

//    private static PropertiesLoader getInstance() {
//        if (instance == null) {
//            synchronized (PropertiesLoader.class) {
//                if (instance == null) {
//                    instance = new PropertiesLoader();
//                    instance.init();
//                }
//            }
//        }
//
//        return instance;
//    }

    private PropertiesLoader() {

    }

    private static void init() {
        try {
            props.load(PropertiesLoader.class.getResourceAsStream("/imserver.properties"));
        } catch (IOException e) {
            throw new RuntimeException("配置文件读取错误", e);
        }
    }

    private String get(String key) {
        return props.getProperty(key);
    }

    public static IMServerNode getIMServerNode() {
//        PropertiesLoader instance = PropertiesLoader.getInstance();
        IMServerNode imServerNode = new IMServerNode(instance.get("im.host"),Integer.parseInt(instance.get("im.port")));
        return imServerNode;
    }

    public static String getIMServerNodeString(){
        String key = instance.get("im.host")+":"+instance.get("im.port");
        return key;
    }

    public static List<String> getZooKeeperServers() {
        String[] servers = instance.get("im.zookeeper.address").split(",");

        //todo: validation
        List<String> serverList = Arrays.asList(servers).stream().filter(a -> true).collect(Collectors.toList());

        if(serverList.isEmpty()) {
            throw new RuntimeException("注册地址无效");
        }

        return serverList;
    }

    public static RegisterType getRegisterType(){
        int registerTypeID = Integer.parseInt(instance.get("im.registertype"));

        RegisterType registerType = RegisterType.fromID(registerTypeID);
        if (registerType == null) {
            throw new RuntimeException("注册类型无效");
        }
        return registerType;
    }

    public static void main(String[] args) {
//        PropertiesLoader instance = PropertiesLoader.getInstance();
        System.out.println(instance.get("im.test"));
    }
}
