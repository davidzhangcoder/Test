package interview.prodconsume.myidea;

public class AirCondition {

    private int num = 0;

    private volatile boolean canAdd = true;

    private volatile boolean canDel = false;

    public synchronized void increment() throws InterruptedException {
        while(!canAdd) {
            wait();
        }

        num++;
        canAdd = false;
        canDel = true;
        System.out.println(num);

        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while(!canDel) {
            wait();
        }
        num--;
        canAdd = true;
        canDel = false;
        System.out.println(num);

        notifyAll();
    }


    //version1 - has issue, not working
//    public synchronized void increment() throws InterruptedException {
//        if(canAdd) {
//            num++;
//            canAdd = false;
//            canDel = true;
//            System.out.println(num);
//            notifyAll();
//        }
//        else
//            wait();
//    }
//
//    public synchronized void decrement() throws InterruptedException {
//        if(canDel) {
//            num--;
//            canAdd = true;
//            canDel = false;
//            System.out.println(num);
//            notifyAll();
//        }
//        else
//            wait();
//    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public boolean isCanDel() {
        return canDel;
    }

    public void setCanDel(boolean canDel) {
        this.canDel = canDel;
    }
}
