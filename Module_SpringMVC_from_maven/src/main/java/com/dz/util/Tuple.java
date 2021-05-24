package com.dz.util;

public class Tuple<K,T> {

    private K obj1;

    private T obj2;

    public Tuple(K obj1, T obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public K first(){
        return obj1;
    }

    public T second(){
        return obj2;
    }
}
