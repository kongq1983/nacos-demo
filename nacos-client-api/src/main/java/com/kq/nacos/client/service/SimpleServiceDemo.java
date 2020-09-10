package com.kq.nacos.client.service;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.kq.nacos.client.config.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SimpleServiceDemo {

    private static final Logger logger = LoggerFactory.getLogger(SimpleServiceDemo.class);

    public static void main(String[] args) throws Exception{

        logger.info("==========================================");

        String tenant = Util.TENANT;
        Properties prop = Util.getProperties(tenant);

        NamingService naming = NamingFactory.createNamingService(prop);

        naming.registerInstance("config.server", "192.168.6.170", 1000, "configServerCluster");

        ServiceRunnable runnable = new ServiceRunnable(naming,"config.server");
//        new Thread(runnable).start();

        System.out.println("---------------------------------------------");

        TimeUnit.MINUTES.sleep(30);

    }

}
