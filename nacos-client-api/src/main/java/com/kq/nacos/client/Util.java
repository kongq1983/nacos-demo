package com.kq.nacos.client;

import java.util.Properties;

public class Util {


    /** nacos server */
    public static final String NACOS_SERVER = "172.16.5.1:8848";
    public static final String TENANT = "8e1690ee-2eff-4bed-bc85-dc585270775d";

    public static Properties getProperties(){

        Properties properties = new Properties();
        properties.put("serverAddr", Util.NACOS_SERVER);

        return properties;
    }


}
