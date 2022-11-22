package com.im.common.message;

public class ClientInitializeRequestMessage extends Message{

    private String userName;

    private long userID;

    @Override
    public MessageType getType() {
        return MessageType.ClientInitializeRequestMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
