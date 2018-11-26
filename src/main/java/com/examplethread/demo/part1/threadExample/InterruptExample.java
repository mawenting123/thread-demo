package com.examplethread.demo.part1.threadExample;

/**
 * sleep、wait、join 均需要抛InterruptedException、都可被打断
 */
public class InterruptExample {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号：" + this.isInterrupted());
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        Thread.sleep(1_000);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());*/

/*        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
                    synchronized (MONITOR){
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到打断信号：" + this.isInterrupted());
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t.start();
        Thread.sleep(1_000);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());*/

        Thread t = new Thread(()->{
            while(true){
                synchronized (MONITOR){
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号：" + Thread.interrupted());
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        Thread.sleep(1_000);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
