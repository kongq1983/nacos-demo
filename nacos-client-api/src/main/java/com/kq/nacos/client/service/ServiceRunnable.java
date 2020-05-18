package com.kq.nacos.client.service;

import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ServiceRunnable implements Runnable{

    private String serviceName;
    private NamingService naming;


    public ServiceRunnable(NamingService naming,String serviceName){
        this.naming = naming;
        this.serviceName = serviceName;
    }

    @Override
    public void run() {

        while (true) {
            try {
                List<Instance> list = naming.selectInstances(serviceName, true);

                for (Instance instance : list) {
                    System.out.println("load instance: " + instance);
                }
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
