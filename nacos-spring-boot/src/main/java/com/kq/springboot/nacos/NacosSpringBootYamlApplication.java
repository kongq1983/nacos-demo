package com.kq.springboot.nacos;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
// 注意要加first=true  否则有问题的
// first = true，那么就只会加载第一次的配置，忽略后面的配置
// 如果first = true 不指定，后面还会加载classpath下的application.yml 这里包括config目录下的都会加载
// 也就是本地配置文件中的属性 会覆盖nacos的值
@NacosPropertySource(dataId = "application.yml",groupId = "test",autoRefreshed = true,type = ConfigType.YAML)
//@NacosPropertySource(dataId = "application.yml",groupId = "test",autoRefreshed = true,type = ConfigType.YAML,first = true)
//@NacosPropertySource(dataId = "application.properties",groupId = "test",autoRefreshed = true,type = ConfigType.PROPERTIES)
@SpringBootApplication
public class NacosSpringBootYamlApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NacosSpringBootYamlApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();

//        for(String beanName : beanNames) {
//            log.info("load beanName ={}",beanName);
//        }
//
//        log.info("load beanNames size ={}",context.getBeanDefinitionCount());

    }


}
