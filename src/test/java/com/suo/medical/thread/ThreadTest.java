package com.suo.medical.thread;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadTest {

//    static  int count = 0;
//    static volatile int count = 0;
    static volatile int count = 0;
    static final Object o = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            synchronized (o){
                for (int i = 0; i < 1000; i++) {
                    count++;
                }
            }
        };
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();//把这个线程数组中的所有线程都插入到主线程之前，让所有线程都跑完，避免主线抢到CPU的执行权，导致读到线程计算结果的中间值
        }


        //Thread.currentThread().sleep(1000);

        System.out.println(count);
        //裸int：88000
        //加volatile(只保证有序性和可见性，不保证原子性):95000
        //加synchronized:92000
    }
}
