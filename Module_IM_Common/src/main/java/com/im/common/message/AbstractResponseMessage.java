package com.im.common.message;

public abstract class AbstractResponseMessage extends Message{

    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public AbstractResponseMessage(boolean isSuccess, String message) {
        super();
        this.isSuccess = isSuccess;
        this.setMessage(message);
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

}
