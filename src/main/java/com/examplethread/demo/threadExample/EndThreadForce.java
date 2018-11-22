package com.examplethread.demo.threadExample;

/**
 * 强制结束线程
 */
public class EndThreadForce {
    public static void main(String[] args)  {
        ThreadService threadService = new ThreadService();
        //加载大型资源，耗时很长很长
        long start = System.currentTimeMillis();
        threadService.execute(()->{
//            while(true){
//
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }
}
