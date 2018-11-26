package com.examplethread.demo.part1.SyncExample;

public class SyncStaticTest {
    public static void main(String[] args) {

        new Thread("t1") {
            @Override
            public void run() {
                SyncStatic.m1();
            }
        }.start();
        new Thread("t2") {
            @Override
            public void run() {
                SyncStatic.m2();
            }
        }.start();
        new Thread("t3") {
            @Override
            public void run() {
                SyncStatic.m3();
            }
        }.start();
    }
}
