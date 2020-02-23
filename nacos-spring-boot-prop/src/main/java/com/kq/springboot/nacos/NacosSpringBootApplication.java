package com.kq.springboot.nacos;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
//@NacosPropertySource 相当于一个配置文件  比如application.properties
//@NacosValue 的 key 从dataId=application.properties的值中，去取
@NacosPropertySource(dataId = "application.properties",groupId = "test",autoRefreshed = true,type = ConfigType.PROPERTIES,first = true)
public class NacosSpringBootApplication {


    public static void main(String[] args) {



        ConfigurableApplicationContext context = SpringApplication.run(NacosSpringBootApplication.class, args);

        log.info("NacosSpringBootApplication argument= {}",args);

        String[] beanNames = context.getBeanDefinitionNames();

        for(String beanName : beanNames) {
            log.info("load beanName ={}",beanName);
        }

        log.info("load beanNames size ={}",context.getBeanDefinitionCount());

    }


}
