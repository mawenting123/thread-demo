package com.examplethread.demo.threadExample;

public class JoinExample2 {
    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Capture("m1",10000L));
        Thread t2 = new Thread(new Capture("m2",30000L));
        Thread t3 = new Thread(new Capture("m3",20000L));

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        //全部线程采集结束后打印日志
        Long end = System.currentTimeMillis();
        System.out.printf("capture all data finish,start:%s,end:%s",start,end);
        System.out.println();
    }

    //采集服务器数据
    private static class Capture implements Runnable{

        private String machineName;
        private Long time;

        public Capture(String machineName, Long time) {
            this.machineName = machineName;
            this.time = time;
        }

        @Override
        public void run() {
            //采集数据耗费time时长
            try {
                Thread.sleep(time);
                System.out.printf("capture data finish at timestamp [%s] and successful",System.currentTimeMillis());
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

