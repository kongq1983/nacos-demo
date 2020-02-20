package com.kq.nacos.client;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.concurrent.Executor;

public class NacosListenerDemo {


    public static void main(String[] args) throws Exception{

//        String tenant = Util.TENANT;
        String dataId = "db.password";
        String group = "test";

        ConfigService configService = NacosFactory.createConfigService(Util.getProperties());
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("NacosListenerDemo:"+content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("receive1:" + configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });

// Keep the main thread alive throughout the test, because the configuration subscription runs in a daemon thread, which exits once the main thread exits. The following code is not required in a real environment.
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
