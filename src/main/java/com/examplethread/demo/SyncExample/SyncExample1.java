package com.examplethread.demo.SyncExample;

public class SyncExample1 {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Runnable runnable = ()->{
            synchronized (LOCK){
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        t1.start();
        t2.start();
        t3.start();

    }
}
