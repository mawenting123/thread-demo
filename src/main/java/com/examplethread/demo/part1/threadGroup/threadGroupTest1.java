package com.examplethread.demo.part1.threadGroup;

public class threadGroupTest1 {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("TG1");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup,"TG2");
        Thread t = new Thread(threadGroup,()->{
            while(true){
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread t2 = new Thread(threadGroup2,()->{
            while(true){
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        System.out.println(threadGroup.getParent());
        System.out.println(t.getThreadGroup());
        System.out.println(t.getThreadGroup().activeCount());
        System.out.println(threadGroup.activeGroupCount());
    }
}
