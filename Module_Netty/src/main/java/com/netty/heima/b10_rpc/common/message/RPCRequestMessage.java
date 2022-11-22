package com.netty.heima.b10_rpc.common.message;

public class RPCRequestMessage extends Message{

    private String classForName;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] parameters;

    private long seqID;

    public RPCRequestMessage(String classForName, String methodName, Class[] parameterTypes, Object[] parameters, long seqID) {
        this.classForName = classForName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
        this.seqID = seqID;
    }

    @Override
    public MessageType getType() {
        return MessageType.RPCRequestMessage;
    }

    public String getClassForName() {
        return classForName;
    }

    public void setClassForName(String classForName) {
        this.classForName = classForName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public long getSeqID() {
        return seqID;
    }

    public void setSeqID(long seqID) {
        this.seqID = seqID;
    }
}
