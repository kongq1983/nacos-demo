package com.kq.http.api.demo;


import com.alibaba.nacos.client.config.impl.HttpSimpleClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * https://nacos.io/zh-cn/docs/open-api.html
 *
 * 请求url: /nacos/v1/cs/configs
 *
 * 请求参数
 * 名称	类型	是否必须	描述
 * tenant	string	否	租户信息，对应 Nacos 的命名空间ID字段。
 * dataId	string	是	配置 ID。
 * group	string	是	配置分组。
 *
 * 返回参数
 * 参数类型	描述
 * string	配置值
 *
 * 错误编码
 * 错误代码	描述	语义
 * 400	Bad Request	客户端请求中的语法错误
 * 403	Forbidden	没有权限
 * 404	Not Found	无法找到资源
 * 500	Internal Server Error	服务器内部错误
 * 200	OK	正常
 */
public class GetValueData {

    // http://172.16.5.1:8848/nacos/v1/cs/configs
    public static final String URL = "http://"+Util.NACOS_SERVER+"/nacos"+Util.URI;

    public static void main(String[] args) throws Exception{

        List<String> headers = new ArrayList<>();
        String dataId = "db.password";
        String group = "test";
        String tenant = Util.TENANT;

        List<String> paramValues = Arrays.asList("dataId", dataId, "group", group, "tenant", tenant);

        HttpSimpleClient.HttpResult httpResult = HttpSimpleClient.httpGet(URL,headers,paramValues,Util.ENCODING,3000L);
        int code = httpResult.code;
        String content = httpResult.content;
        Map<String,List<String>> resultHeaders = httpResult.headers;

        System.out.println("code="+code+" content="+content);
        System.out.println("resultHeaders="+resultHeaders);


    }

}
