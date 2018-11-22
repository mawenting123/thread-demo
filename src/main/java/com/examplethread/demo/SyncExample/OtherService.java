package com.examplethread.demo.SyncExample;

public class OtherService {
    private static final Object LOCK = new Object();

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    public void s1() {
        synchronized (LOCK){
            System.out.println("s1-----------");
        }
    }
    public void s2() {
        synchronized (LOCK){
            System.out.println("s2-----------");
            deadLock.m2();
        }
    }
}
