package com.examplethread.demo.threadExample;

/**
 * interrupt打断main线程
 */
public class InterruptExample2 {

    public static void main(String[] args){
        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
                    System.out.println("---");
                }
            }
        };
        t.start();

        Thread mainThread = Thread.currentThread();

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();
            System.out.println("interrupt");
        });
        t2.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("interrupt1");
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
