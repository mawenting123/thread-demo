package com.examplethread.demo.threadExample;

/**
 * 优雅的结束线程
 */
public class EndThreadGarceful2 {
    public static void main(String[] args) throws InterruptedException {
        Worker t = new Worker();
        t.start();
        Thread.sleep(10000);
        t.interrupt();
    }

    private static class Worker extends Thread{
        @Override
        public void run() {
            while(true){
                //1.
/*                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break; //return /使用break线程中断,break后的逻辑可以执行
                }
                System.out.println("interrupt");
                */
                //2.
                try {
                    Thread.sleep(1);
                    if(this.isInterrupted()){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
