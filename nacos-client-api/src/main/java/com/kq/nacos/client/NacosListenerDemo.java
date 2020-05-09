package com.kq.nacos.client;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Properties;
import java.util.concurrent.Executor;

public class NacosListenerDemo {


    public static void main(String[] args) throws Exception{

//        String tenant = Util.TENANT;
        String dataId = "db.password";
        String group = "test";
        String namespace = Util.TENANT;

        // 有以下3种方式 实现tenant
//        System.setProperty("acm.namespace", Util.TENANT); //优先级最高

        // 设置环境变量ALIBABA_ALIWARE_NAMESPACE=a9e6fb45-6b7a-4664-af7b-8b9c538ed46a
//        System.getenv(PropertyKeyConst.SystemEnv.ALIBABA_ALIWARE_NAMESPACE); //设置环境变量 或者idea地方环境变量
        // properties.put("namespace",namespace);


        ConfigService configService = NacosFactory.createConfigService(Util.getProperties(namespace));
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println("NacosListenerDemo:"+content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("db.password modify , new data :" + configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });

        String urlDataId = "url";
        configService.addListener(urlDataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("url modify , new data :" + configInfo);
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
