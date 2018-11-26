package com.examplethread.demo.part1.oneProduceConsumeExample;

/**
 * 生产与消费均陷入等待的错误代码
 */
public class ProduceConsumeV3 {
    private int i = 1;
    private final Object LOCK = new Object();

    private volatile boolean isProduce = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduce) {
                LOCK.notify();
            } else {
                i++;
                System.out.println("p>>"+i);
                isProduce = true;
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduce) {
                System.out.println("c>>"+i);
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                LOCK.notify();
            }
        }
    }

    public static void main(String[] args) {
        ProduceConsumeV3 v3 = new ProduceConsumeV3();
        new Thread("p"){
            @Override
            public void run() {
                v3.produce();
            }
        }.start();
        new Thread("c"){
            @Override
            public void run() {
                v3.consume();
            }
        }.start();
    }
}
