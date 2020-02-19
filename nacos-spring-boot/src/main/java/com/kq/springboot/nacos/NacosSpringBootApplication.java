package com.kq.springboot.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class NacosSpringBootApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NacosSpringBootApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();

        for(String beanName : beanNames) {
            log.info("load beanName ={}",beanName);
        }

        log.info("load beanNames size ={}",context.getBeanDefinitionCount());

    }


}
