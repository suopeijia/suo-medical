package com.suo.medical.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicIntegerTest {
    static AtomicInteger count = new AtomicInteger(0);
    //为了防止ABA问题，使用AtomixStampedRdeference
    //不仅能够比较值，还可以比较版本号
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        count.incrementAndGet();
                }
            };
            Thread[] threads = new Thread[100];
            for (int i1 = 0; i1 < 100; i1++) {
                threads[i1] = new Thread(task);
                threads[i1].start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
            };

        System.out.println(count.get());
    }
    }

