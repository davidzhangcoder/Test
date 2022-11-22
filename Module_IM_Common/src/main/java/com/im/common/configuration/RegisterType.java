package com.im.common.configuration;

public enum RegisterType {

    ZooKeeper(1),
    Euraka(2);

    private int id;

    RegisterType(int id){
        this.id =id;
    }

    public static RegisterType fromID(int id) {
        for (RegisterType value : values()) {
            if(value.id == id)
                return value;
        }
        return null;
    }
}
