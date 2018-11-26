package com.examplethread.demo.part1.threadExample;

import java.util.stream.Stream;

public class DiffrenceOfWaitAndSleep {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
//        m1();
//        m2();
        Stream.of("t1","t2").forEach(name->
            new Thread(name){
                @Override
                public void run() {
//                    m1();
                    m2();
                }
            }.start()
        );
    }

    public static void m1() {
        synchronized (LOCK) {
            System.out.println("the thread " + Thread.currentThread().getName() + " enter.");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m2() {
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
