package com.examplethread.demo.part1.threadExample;

/**
 * sleep、wait区别
 * 1 sleep，是Thread的方法、wait，是Object的方法
 * 2 sleep不会释放object monitor（Lock），wait会释放Object monitor，并将线程加入到Object monitor的waiting queue
 * 3 使用sleep不依赖monitor，但是wait依赖
 * 4 使用sleep不需要被唤醒，但是wait需要（wait（等待时间）除外）
 */
public class WaitAndSleepExample {
}
