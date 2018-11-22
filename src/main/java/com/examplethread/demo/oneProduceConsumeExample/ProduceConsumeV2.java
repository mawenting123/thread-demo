package com.examplethread.demo.oneProduceConsumeExample;

/**
 * 生产一个消费一个
 * 当多个生产者、多个消费者时出现全部线程陷入等待
 */
public class ProduceConsumeV2 {
    private int i = 1;
    private final Object LOCK = new Object();

    /**
     * 是否生产
     */
    private volatile boolean isProduce = false;//volatile作用？？

    public void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                try {
                    //等待消费
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println(Thread.currentThread().getName() + ">>" + i);
                //通知消费
                LOCK.notify();
                isProduce = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                //消费
                System.out.println(Thread.currentThread().getName() + ">>" + i);
                //通知生产
                LOCK.notify();
                isProduce = false;
            } else {
                //等待生产
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumeV2 v2 = new ProduceConsumeV2();
        new Thread("p1") {
            @Override
            public void run() {
                while (true) {
                    v2.produce();
                }
            }
        }.start();
        new Thread("p2") {
            @Override
            public void run() {
                while (true) {
                    v2.produce();
                }
            }
        }.start();
        new Thread("c1") {
            @Override
            public void run() {
                while (true) {
                    v2.consume();
                }
            }
        }.start();
        new Thread("c2") {
            @Override
            public void run() {
                while (true) {
                    v2.consume();
                }
            }
        }.start();
    }
}
