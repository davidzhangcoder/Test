package interview.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test6Data {

    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    Condition c4 = lock.newCondition();

    StringBuffer file1 = new StringBuffer();
    StringBuffer file2 = new StringBuffer();
    StringBuffer file3 = new StringBuffer();
    StringBuffer file4 = new StringBuffer();

    volatile boolean flag1 = true;
    volatile boolean flag2 = false;
    volatile boolean flag3 = false;
    volatile boolean flag4 = false;
    volatile boolean canDo = true;

//    编写一个程序，程序会启动4个线程，向4个文件A，B，C，D里写入数据，每个线程只能写一个值。
//    线程A：只写A
//    线程B：只写B
//    线程C：只写C
//    线程D：只写D
//
//4个文件A，B，C，D。
//
//    程序运行起来，4个文件的写入结果如下：
//    A：ABCDABCD...
//    B：BCDABCDA...
//    C：CDABCDAB...
//    D：DABCDABC...

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            while (!flag1) {
                c1.await();
            }

            file1.append("A");
            if( file2.length()>0 )
                file2.append("A");
            if( file3.length()>0 )
                file3.append("A");
            if( file4.length()>0 )
                file4.append("A");

            flag2=true;
            flag1=false;
            c2.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            while (!flag2) {
                c2.await();
            }

            file1.append("B");
            file2.append("B");
            if( file3.length()>0 )
                file3.append("B");
            if( file4.length()>0 )
                file4.append("B");

            flag3=true;
            flag2=false;
            c3.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            while (!flag3) {
                c3.await();
            }

            file1.append("C");
            file2.append("C");
            file3.append("C");
            if( file4.length()>0 )
                file4.append("C");

            flag4=true;
            flag3=false;
            c4.signal();
        }
        finally {
            lock.unlock();
        }
    }

    public void printD() throws InterruptedException {
        lock.lock();
        try {
            while (!flag4) {
                c4.await();
            }

            file1.append("D");
            file2.append("D");
            file3.append("D");
            file4.append("D");

            flag1=true;
            flag4=false;
            c1.signal();
        }
        finally {
            lock.unlock();
        }
    }
}

public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        Test6Data test6Data = new Test6Data();

        new Thread(()->{
            while(test6Data.canDo){
                try {
                    test6Data.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"1").start();

        new Thread(()->{
            while(test6Data.canDo){
                try {
                    test6Data.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"2").start();

        new Thread(()->{
            while(test6Data.canDo){
                try {
                    test6Data.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"3").start();

        new Thread(()->{
            while(test6Data.canDo){
                try {
                    test6Data.printD();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"4").start();

        TimeUnit.MILLISECONDS.sleep(1);

        System.out.println("file1=" + test6Data.file1.toString());
        System.out.println("file2=" + test6Data.file2.toString());
        System.out.println("file3=" + test6Data.file3.toString());
        System.out.println("file4=" + test6Data.file4.toString());
    }
}
