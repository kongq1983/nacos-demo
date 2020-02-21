package com.kq.springboot.nacos.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosInjected
    private ConfigService configService;

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue(value = "${db.password:-1}", autoRefreshed = true)
    private String dbPassword;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping(value = "/getDbPassword", method = RequestMethod.GET)
    @ResponseBody
    public String getDbPassword() {
        return dbPassword;
    }


    @RequestMapping(value = "/getDbPassword1", method = RequestMethod.GET)
    @ResponseBody
    public String getDbPassword1() throws Exception{

        String pasword = configService.getConfig("db.password", "test", 3000);

        return pasword;
    }
}