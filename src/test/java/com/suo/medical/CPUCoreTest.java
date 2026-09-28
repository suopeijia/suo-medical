package com.suo.medical;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CPUCoreTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
