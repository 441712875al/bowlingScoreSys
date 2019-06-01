package com.ncu.example;

import com.ncu.example.pojo.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;


@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ExampleApplication.class, args);
        Manager manager = context.getBean(Manager.class);
        Properties properties = manager.getEliteGrade(4,"along");
        System.out.println(properties.getProperty("pid")+"  "+properties.getProperty("name")+"   " +
                properties.getProperty("tolScore")+"   "+properties.getProperty("contestType"));
    }
}
