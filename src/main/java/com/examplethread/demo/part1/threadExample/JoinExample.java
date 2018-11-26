package com.examplethread.demo.part1.threadExample;

import java.util.Vector;
import java.util.stream.IntStream;

/**
 * join()
 */
public class JoinExample {
    public static void main(String[] args) throws InterruptedException {

        //可以设置join等待内层线程的时间，过了设置的等待时间后不再等待，继续执行外层线程
/*        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3_000);
                    System.out.println(Thread.currentThread().getName() + "----------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.join(2_000);
        System.out.println(Thread.currentThread().getName());*/

//t1,t2交替执行（t1,t2不是顺序执行），t1,t2结束后，再执行最外层线程
/*
        Thread t1 = new Thread(){
            @Override
            public void run() {
                IntStream.range(1,100).forEach((i)-> {
                    System.out.println(Thread.currentThread().getName() + "===" + i);
                });
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                IntStream.range(1,100).forEach((i)->{
                    System.out.println(Thread.currentThread().getName() + "==="+ i);
                });
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(Thread.currentThread().getName());*/

    Vector<Thread> vector = new Vector();
    IntStream.range(1,10).forEach((k)->{
        Thread t = new Thread(){
            @Override
            public void run() {
                IntStream.range(1,1000).forEach((i)-> {
                    System.out.println(Thread.currentThread().getName() + "===" + k + "===" + i);
                });
            }
        };
        t.start(); //start放在这里，所有线程“交替”进行
        vector.add(t);

    });
        for(Thread t: vector){
            //t.start(); //start放在这里，所有线程“顺序”进行
            t.join();
        }

        System.out.println(Thread.currentThread().getName());
    }
}
