package com.im.common.message;

public class ChatRequestMessage extends Message{

    private long fromID;
    private String from;

    private long toID;
    private String to;

    public ChatRequestMessage(long fromID, String from, long toID, String to, String message) {
        this.fromID = fromID;
        this.from = from;
        this.toID = toID;
        this.to = to;
        this.setMessage(message);
    }

    @Override
    public MessageType getType() {
        return MessageType.ChatRequestMessage;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public long getFromID() {
        return fromID;
    }

    public void setFromID(long fromID) {
        this.fromID = fromID;
    }

    public long getToID() {
        return toID;
    }

    public void setToID(long toID) {
        this.toID = toID;
    }
}
