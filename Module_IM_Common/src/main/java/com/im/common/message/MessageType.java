package com.im.common.message;

public enum MessageType {

    ChatRequestMessage(1),
    ChatResponseMessage(2),
    ChatRequestNotificationMessage(3),
    ACKChatRequestMessage(4),
    ACKChatResponseMessage(5),
    ACKChatRequestNotificationMessage(6),
    GroupChatRequestMessage(7),
    GroupChatResponseMessage(8),

    ClientInitializeRequestMessage(9);

    private final long id;

    private MessageType(long id){
        this.id = id;
    }

}
