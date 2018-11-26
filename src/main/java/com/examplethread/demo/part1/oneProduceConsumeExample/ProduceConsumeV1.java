package com.examplethread.demo.part1.oneProduceConsumeExample;

/**
 * 生产与消费没有互动
 */
public class ProduceConsumeV1 {
    private int i = 1;
    private final Object LOCK = new Object();

    public void produce() {
        synchronized (LOCK) {
            System.out.println("p>>" + (i++));
        }
    }

    public void consume() {
        synchronized (LOCK) {
            System.out.println("c>>" + i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumeV1 v1 = new ProduceConsumeV1();
        new Thread("p") {
            @Override
            public void run() {
                while (true) {
                    v1.produce();
                }
            }
        }.start();
        new Thread("c") {
            @Override
            public void run() {
                while (true) {
                    v1.consume();
                }
            }
        }.start();
    }
}
