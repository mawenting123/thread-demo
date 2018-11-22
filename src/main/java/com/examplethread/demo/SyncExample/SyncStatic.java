package com.examplethread.demo.SyncExample;

/**
 * class锁
 */
public class SyncStatic {

    /**
     * 静态代码块 加锁是class锁
     */
    static {
        synchronized (SyncStatic.class){
            try {
                System.out.println("m "+Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        try {
            System.out.println("m1 "+Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        try {
            System.out.println("m2 "+Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不需抢锁，立即执行
     */
    public static void m3() {
        try {
            System.out.println("m3 "+Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
