package com.examplethread.demo.part1.threadExample;

/**
 * 守护线程
 */
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        //Thread命名
/*        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread threadS = new Thread("s");
        Thread thread3 = new Thread();
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());
        System.out.println(threadS.getName());
        System.out.println(thread3.getName());
        System.out.println(Thread.currentThread().getName());*/

        //守护线程
    /*    Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(25000);
                    System.out.println(Thread.currentThread().getName() + " done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("run.");
            }
        },"mwt");
        //守护线程setDaemon在start之前
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(20000);
        System.out.println(Thread.currentThread().getName());*/

        Thread thread = new Thread(()->{
            Thread innerThread = new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " running");
                    Thread.sleep(10_000);
                    System.out.println(Thread.currentThread().getName() + " done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"innerThread");
//            innerThread.setDaemon(true);
            innerThread.start();
//            try {
//                innerThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            try {
                Thread.sleep(5_000);
                System.out.println("outThread thread finished done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"outThread");
        thread.start();
    }
}
