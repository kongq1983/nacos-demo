package com.kq.springboot.nacos.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class MyConfigController {

//    @NacosValue(value = "${elasticsearch.server:'http://172.16.5.1:9200'}", autoRefreshed = true)
    @NacosValue(value = "${elasticsearch.server}", autoRefreshed = true)
    private String elasticSearchServer;


    @NacosValue(value = "${spring.datasource.url}", autoRefreshed = true)
    private String jdbcUrl;


    @RequestMapping(value = "/getElasticSearchServer", method = RequestMethod.GET)
    @ResponseBody
    public String getElasticSearchServer() {
        return elasticSearchServer;
    }

    @RequestMapping(value = "/getJdbcUrl", method = RequestMethod.GET)
    @ResponseBody
    public String jdbcUrl() {
        return jdbcUrl;
    }


}
