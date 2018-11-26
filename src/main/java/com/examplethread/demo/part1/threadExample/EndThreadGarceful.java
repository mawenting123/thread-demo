package com.examplethread.demo.part1.threadExample;

/**
 * 优雅的结束线程
 */
public class EndThreadGarceful {
    public static void main(String[] args) {
        Worker t = new Worker();
        t.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.shutdown();
    }

//    class MyThread extends Thread{
//        private boolean start = true;
//        public void execute(){
//
//        }
//
//        public void shutdown(long mills){
//            this.start = false;
//        }
//    }

    private static class Worker extends Thread{
        private volatile boolean start = true;

        @Override
        public void run() {
            while(start){

            }
        }

        public void shutdown(){
            this.start=false;
        }
    }
}
