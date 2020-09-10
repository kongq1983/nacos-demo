package com.kq.nacos.client.service;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.kq.nacos.client.config.Util;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SelectInstancesDemo {

    public static void main(String[] args) throws Exception{
        String tenant = Util.TENANT;
        Properties prop = Util.getProperties(tenant);

        NamingService naming = NamingFactory.createNamingService(prop);

        List<Instance> list = naming.selectInstances(Util.CONFIG_SERVER_KEY, true);

        System.out.println("list="+list);

        TimeUnit.MINUTES.sleep(30);

    }

}
