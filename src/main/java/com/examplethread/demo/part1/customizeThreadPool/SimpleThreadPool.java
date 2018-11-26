package com.examplethread.demo.part1.customizeThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 自定义简易线程池
 * 初始化线程个数并创建线程任务队列和线程队列
 */
public class SimpleThreadPool {

    /**
     * 默认线程个数
     */
    private static final int DEFAULT_SIZE = 10;
    /**
     * 初始线程个数
     */
    private int size;

    /**
     * 线程名前缀下标
     */
    private static volatile int seq = 0;

    /**
     * 线程名前缀
     */
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL";

    /**
     * 线程组名称
     */
    private static final ThreadGroup GROUP = new ThreadGroup("poolGroup");

    /**
     * 线程池中线程
     */
    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    /**
     * 待处理的任务队列
     */
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size ; i++) {
            createWorkerTask();
        }
    }

    public void submit(Runnable runnable){
        synchronized (TASK_QUEUE){
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    /**
     * 线程状态
     */
    private enum TaskStatus {
        FREE, RUNNING, BLOCK, DEAD
    }

    private void createWorkerTask() {
        WorkerTask workerTask = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        workerTask.start();
        THREAD_QUEUE.add(workerTask);
    }

    private static class WorkerTask extends Thread {

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        private volatile TaskStatus taskStatus = TaskStatus.FREE;

        public TaskStatus getTaskStatus() {
            return this.taskStatus;
        }

        public void close() {
            this.taskStatus = TaskStatus.DEAD;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskStatus != TaskStatus.DEAD) {
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskStatus = TaskStatus.BLOCK;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            break OUTER;
                        }
                    }
                    //取队列第一个并移除
                    Runnable runnable = TASK_QUEUE.removeFirst();
                    if (runnable != null){
                        taskStatus = TaskStatus.RUNNING;
                        runnable.run();
                        taskStatus = TaskStatus.FREE;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool pool = new SimpleThreadPool();
        IntStream.rangeClosed(0,100).forEach(i->{
            pool.submit(()->{
                System.out.println("The runnable "+ i+" is serviced by "+Thread.currentThread()+" start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable "+ i+" is serviced by "+Thread.currentThread() + " finished.");
            });
        });
    }
}
