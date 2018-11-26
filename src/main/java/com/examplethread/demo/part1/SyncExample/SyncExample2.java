package com.examplethread.demo.part1.SyncExample;

/**
 * synchronized方法与代码块
 */
public class SyncExample2 {

    public static void main(String[] args) {
/*        ThisLock thisLock = new ThisLock();
        new Thread("t1"){
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();
        new Thread("t2"){
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();  */

        ThisLock2 thisLock2 = new ThisLock2();
        new Thread("t1") {
            @Override
            public void run() {
                thisLock2.m1();
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                thisLock2.m2();
            }
        }.start();
    }
}

class ThisLock {
    public void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThisLock2 {

    private final Object LOCK = new Object();

    /**
     * this锁
     */
    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}