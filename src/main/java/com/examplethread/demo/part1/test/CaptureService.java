package com.examplethread.demo.part1.test;

import java.util.Arrays;

public class CaptureService {
    public static void main(String[] args) {
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10")
                .stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                });
    }

    public static Thread createCaptureThread(String name) {
        return new Thread(() -> {

        }, name);
    }
}
