package com.netty.netty_pool;



/**
 * Netty自带连接池测试类，SpringServer为连接池启动类
 * 
 * @author Administrator
 *
 */
public class MyNettyPoolTest {

    public static void main(String[] args) {

        
        MyNettyPool pool = new MyNettyPool();
        new MyThread(pool, "hello").start();
//        new MyThread(pool, user, command).start();
//        new MyThread(pool, user, command).start();
//        new MyThread(pool, user, command).start();
        for (int i = 0; i < 30; i++) {
            new MyThread(pool, "world").start();
        }

    }

}

class MyThread extends Thread {

    public MyNettyPool pool;
    public String msg;

    public MyThread(MyNettyPool pool, String msg) {
        super();
        this.pool = pool;
        this.msg = msg;
    }

    @Override
    public void run() {
        pool.send(msg);
        //System.out.println(response);
    }

}