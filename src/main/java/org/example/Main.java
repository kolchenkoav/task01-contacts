package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.config.AppConfig;
import org.example.service.MyCommandHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("Spring context initialized");
        context.getBean(MyCommandHandler.class).run();
    }
}