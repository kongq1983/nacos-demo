package com.kq.service.register;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

public class SimpleServiceDemo {

    public static void main(String[] args) {

        NamingService naming = NamingFactory.createNamingService(System.getProperty("serveAddr"));
        naming.registerInstance("nacos.test.3", "11.11.11.11", 8888, "TEST1");

    }

}
