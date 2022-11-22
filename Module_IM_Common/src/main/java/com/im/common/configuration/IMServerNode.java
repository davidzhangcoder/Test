package com.im.common.configuration;

import java.util.Objects;

public class IMServerNode {

    private String host;

    private int port;

    public IMServerNode(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IMServerNode that = (IMServerNode) o;
        return port == that.port &&
                Objects.equals(host, that.host);
    }

    public String getServerKey(){
        return "IMServerNode:"+host+":"+port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    @Override
    public String toString() {
        return "IMServerNode:" +
                "host=" + host +
                ":port=" + port;
    }
}
