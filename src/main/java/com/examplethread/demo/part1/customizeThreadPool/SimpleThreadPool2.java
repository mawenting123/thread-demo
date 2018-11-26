package com.examplethread.demo.part1.customizeThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 自定义简易线程池
 * 1 初始化线程个数并创建线程任务队列和线程队列
 * 2 添加线程池状态，拒绝策略（给任务队列添加过多任务时拒绝再添加）
 * 3 线程池状态、线程池shutdown
 */
public class SimpleThreadPool2 {

    /**
     * 默认线程个数
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 默认任务队列数量
     */
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;
    /**
     * 初始线程个数
     */
    private int size;

    /**
     * 任务队列数量
     */
    private int taskQueueSize;

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

    /**
     * 默认拒绝策略
     */
    private static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("");
    };

    private DiscardPolicy discardPolicy;

    private volatile boolean destroy = false;

    public SimpleThreadPool2() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool2(int size, int taskQueueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.taskQueueSize = taskQueueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkerTask();
        }
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("pool is destroyed and not allowed submit.");
        }
        synchronized (TASK_QUEUE) {
            //拒绝添加任务
            if (TASK_QUEUE.size() > taskQueueSize) {
                discardPolicy.discard();
            } else {
                TASK_QUEUE.addLast(runnable);
                TASK_QUEUE.notifyAll();
            }
        }
    }

    /**
     * 停止线程池
     */
    public void shutdown() throws InterruptedException {
        //有正在执行的任务
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask workerTask : THREAD_QUEUE) {
                if (workerTask.getTaskStatus() == TaskStatus.BLOCK) {
                    workerTask.interrupt();
                    workerTask.taskStatus = TaskStatus.DEAD;
                    initVal--;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        this.destroy = true;
        System.out.println("the thread pool is destroyed");
    }

    public int getSize() {
        return size;
    }

    public int getTaskQueueSize() {
        return taskQueueSize;
    }

    public boolean isDestroy() {
        return destroy;
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

    /**
     * 拒绝异常
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 拒绝策略接口
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
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
                    if (runnable != null) {
                        taskStatus = TaskStatus.RUNNING;
                        runnable.run();
                        taskStatus = TaskStatus.FREE;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool2 pool = new SimpleThreadPool2();
        IntStream.rangeClosed(0, 30).forEach(i -> {
            pool.submit(() -> {
                System.out.println("The runnable " + i + " is serviced by " + Thread.currentThread() + " start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable " + i + " is serviced by " + Thread.currentThread() + " finished.");
            });
        });
        Thread.sleep(10_000);
        pool.shutdown();
        pool.submit(() -> {
            System.out.println("submit after pool is destroyed");
        });
    }
}
