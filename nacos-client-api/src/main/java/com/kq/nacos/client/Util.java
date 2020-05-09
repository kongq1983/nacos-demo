package com.kq.nacos.client;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class Util {


    /** nacos server */
    public static final String NACOS_SERVER = "172.16.5.1:8848";
//    public static final String TENANT = "8e1690ee-2eff-4bed-bc85-dc585270775d";

//    public static final String TENANT = "d0883df9-0fd9-449e-b254-8acc4da0bf99";
    public static final String TENANT = "a9e6fb45-6b7a-4664-af7b-8b9c538ed46a";

    public static Properties getProperties(String namespace){

        Properties properties = new Properties();
        properties.put("serverAddr", Util.NACOS_SERVER);
        if(StringUtils.isNotBlank(namespace)) {
            properties.put("namespace",namespace);
        }

        return properties;
    }


}
