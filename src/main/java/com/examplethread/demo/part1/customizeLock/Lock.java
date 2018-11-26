package com.examplethread.demo.part1.customizeLock;

import java.util.Collection;

public interface Lock {

    class TimeOutExecption extends Exception{
        public TimeOutExecption(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException,TimeOutExecption;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockSize();
}
