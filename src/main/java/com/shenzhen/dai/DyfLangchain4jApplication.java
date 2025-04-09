package com.shenzhen.dai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.shenzhen.dai.expense.mapper", "com.shenzhen.dai.langchain4j.mapper"})
public class DyfLangchain4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyfLangchain4jApplication.class, args);
    }

}
