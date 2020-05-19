package com.kq.nacos.client.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

import java.util.Arrays;
import java.util.List;

public class NacosDemo1 {

    public static void main1(String[] args) throws Exception{

        String tenant = Util.TENANT;
        String dataId = "db.password";
        String group = "test";

        // 其实就是调用 ConfigFactory.createConfigService(properties)
        ConfigService configService = NacosFactory.createConfigService(Util.getProperties(tenant));
        // Actively get the configuration.
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);


    }

    public static void main(String[] args) {
        String[] abc = {"12","33"};

        List<String> list = Arrays.asList(abc);
        System.out.println(list);
    }

}
