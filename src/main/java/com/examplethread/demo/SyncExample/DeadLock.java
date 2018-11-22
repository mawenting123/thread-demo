package com.examplethread.demo.SyncExample;

/**
 * 死锁
 */
public class DeadLock {

    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private static final Object LOCK = new Object();

    public void m1(){
        synchronized (LOCK){
            System.out.println("m1");
            otherService.s1();
        }
    }
    public void m2(){
        synchronized (LOCK){
            System.out.println("m2");
        }
    }
}
