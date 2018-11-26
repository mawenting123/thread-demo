package com.examplethread.demo.part1.customizeLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * 实现自己的显示锁
 */
public class BooleanLock implements Lock {

    private boolean initValue;

    public BooleanLock() {
        this.initValue = false;
    }

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;

    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeOutExecption {

    }

    @Override
    public void unlock() {
        this.initValue = false;
        Optional.of(Thread.currentThread().getName() + " release the lock monitor.").ifPresent(System.out::println);
        this.notifyAll();
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockSize() {
        return blockedThreadCollection.size();
    }
}
