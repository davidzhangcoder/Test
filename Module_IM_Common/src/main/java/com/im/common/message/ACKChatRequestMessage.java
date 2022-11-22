package com.im.common.message;

public class ACKChatRequestMessage extends ChatRequestMessage{

    public ACKChatRequestMessage(long fromID, String from, long toID, String to, String message) {
        super(fromID, from, toID, to, message);
    }

    @Override
    public MessageType getType() {
        return MessageType.ACKChatRequestMessage;
    }
}
