package com.examplethread.demo.part1.moreProduceConsumeExample;

import java.util.stream.Stream;

/**
 * 生产一个消费一个
 */
public class ProduceConsume {
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
                LOCK.notifyAll();
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
                LOCK.notifyAll();
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
        ProduceConsume v2 = new ProduceConsume();
        Stream.of("p1", "p2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        v2.produce();
                    }
                }
            }.start();
        });
        Stream.of("c1", "c2").forEach(n -> {
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        v2.consume();
                    }
                }
            }.start();
        });
    }
}
