package com.examplethread.demo.threadExample;

/**
 * 优先级、设置优先级并不能影响线程的执行顺序
 */
public class PriorityExample {
    public static void main(String[] args) {
        MyThread t0 =new MyThread();
        MyThread t1 =new MyThread();
        MyThread t2 =new MyThread();
        t0.setPriority(Thread.MIN_PRIORITY);
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t0.start();
        t1.start();
        t2.start();
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
