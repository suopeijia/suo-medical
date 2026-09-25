package com.suo.medical;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.suo.medical.mapper")
public class SuoMedicalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuoMedicalApplication.class, args);
    }

}
