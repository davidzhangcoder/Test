package com.netty.heima.b5_protocol;

public class LoginRequestMessage extends Message{
    private String userName;

    public LoginRequestMessage(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginRequestMessage{" +
                "userName='" + userName + '\'' +
                '}';
    }

    @Override
    public int getType() {
        return 0;
    }
}
