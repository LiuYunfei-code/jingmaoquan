package com.project.jingmaoquan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.jingmaoquan.mapper")
public class JingmaoquanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JingmaoquanApplication.class, args);
    }

}
