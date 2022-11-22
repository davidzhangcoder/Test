package com.netty.heima.b10_rpc.common.message;

public class RPCResponseMessage extends AbstractResponseMessage{
    private Object result;

    private Class resultType;

    private long seqID;

    public RPCResponseMessage() {
    }

    public RPCResponseMessage(boolean isSuccess, String causeMessage) {
        super(isSuccess, causeMessage);
    }

    @Override
    public MessageType getType() {
        return MessageType.RPCResponseMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Class getResultType() {
        return resultType;
    }

    public void setResultType(Class resultType) {
        this.resultType = resultType;
    }

    public long getSeqID() {
        return seqID;
    }

    public void setSeqID(long seqID) {
        this.seqID = seqID;
    }
}
