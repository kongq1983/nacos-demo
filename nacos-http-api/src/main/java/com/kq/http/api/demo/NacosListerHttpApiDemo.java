package com.kq.http.api.demo;


import com.alibaba.nacos.api.common.Constants;
import com.alibaba.nacos.client.config.impl.HttpSimpleClient;
import com.alibaba.nacos.client.config.utils.MD5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.alibaba.nacos.api.common.Constants.LINE_SEPARATOR;
import static com.alibaba.nacos.api.common.Constants.WORD_SEPARATOR;

/**
 * https://nacos.io/zh-cn/docs/open-api.html
 * 监听配置
 * 描述
 * 监听 Nacos 上的配置，以便实时感知配置变更。如果配置变更，则用获取配置接口获取配置的最新值，动态刷新本地缓存。
 *
 * 注册监听采用的是异步 Servlet 技术。注册监听本质就是带着配置和配置值的 MD5 值和后台对比。如果 MD5 值不一致，就立即返回不一致的配置。如果值一致，就等待住 30 秒。返回值为空。
 *
 * 请求类型
 * POST
 *
 * 请求URL
 * /nacos/v1/cs/configs/listener
 *
 * 请求参数
 * 名称
 * 类型
 * 是否必须
 * 描述
 * Listening-Configs
 * string
 * 是
 * 监听数据报文。格式为 dataId^2Group^2contentMD5^2tenant^1或者dataId^2Group^2contentMD5^1。
 * dataId：配置 ID
 * group：配置分组
 * contentMD5：配置内容 MD5 值
 * tenant：租户信息，对应 Nacos 的命名空间字段(非必填)
 * Header 参数
 * 名称	类型	是否必须	描述
 * Long-Pulling-Timeout	string	是	长轮训等待 30s，此处填写 30000。
 * 参数说明
 * 配置多个字段间分隔符：^2 = Character.toString((char) 2
 * 配置间分隔符：^1 = Character.toString((char) 1)
 * contentMD5: MD5(content)，第一次本地缓存为空，所以这块为空串
 * 返回参数
 * 参数类型	描述
 * string	配置值
 * 错误编码
 * 错误代码	描述	语义
 * 400	Bad Request	客户端请求中的语法错误
 * 403	Forbidden	没有权限
 * 404	Not Found	无法找到资源
 * 500	Internal Server Error	服务器内部错误
 * 200	OK	正常
 * 示例
 * 请求示例
 * http://serverIp:8848/nacos/v1/cs/configs/listener
 *
 * POST 请求体数据内容：
 *
 * Listening-Configs=dataId^2group^2contentMD5^2tenant^1
 * 返回示例
 * 如果配置变化
 *
 * dataId^2group^2tenant^1
 *
 * 如果配置无变化：会返回空串
 */
public class NacosListerHttpApiDemo {

    public static final String URL = "http://"+Util.NACOS_SERVER+"/nacos"+Util.URI+"/listener";

    public static void main(String[] args) throws Exception{

        String dataId = "db.password";
        String group = "test";
        String tenant = Util.TENANT;
        // tenant: a9e6fb45-6b7a-4664-af7b-8b9c538ed46a
        String name = "fixed-172.16.5.1_8848-a9e6fb45-6b7a-4664-af7b-8b9c538ed46a"; //默认nacos-client生成名称规则
//        String name = "fixed1-172.16.5.1_8848-a9e6fb45-6b7a-4664-af7b-8b9c538ed46a";

        // db.password的值
//        String content = "1234567";
        String content = "12345678901";

        String contentMd5 = MD5.getInstance().getMD5String(content);

        StringBuilder sb = new StringBuilder();
        sb.append(dataId).append(WORD_SEPARATOR);
        sb.append(group).append(WORD_SEPARATOR);
        sb.append(contentMd5).append(WORD_SEPARATOR);
        sb.append(tenant).append(LINE_SEPARATOR);

        System.out.println("Listening-Configs="+sb.toString());

        List<String> params = Arrays.asList(Constants.PROBE_MODIFY_REQUEST, sb.toString());

        List<String> headers = new ArrayList<String>(2);
        headers.add("Long-Pulling-Timeout");
        headers.add("" + 30000);

        boolean isInitializingCacheList = true;
        // told server do not hang me up if new initializing cacheData added in
        if (isInitializingCacheList) {
            headers.add("Long-Pulling-Timeout-No-Hangup");
            headers.add("true");
        }

        long startTime = System.currentTimeMillis();
        HttpSimpleClient.HttpResult httpResult = HttpSimpleClient.httpPost(
                URL, headers, params, Util.ENCODING,
                30000, false);

        long endTime = System.currentTimeMillis();

        int code = httpResult.code;
        String resultContent = httpResult.content;
        Map<String,List<String>> resultHeaders = httpResult.headers;
        System.out.println("code="+code);
        // 没有变化 content=空   有变化
        System.out.println("content="+resultContent);
        System.out.println("resultHeaders="+resultHeaders);
        System.out.println("spendTime="+(endTime-startTime)+"ms");

        TimeUnit.MINUTES.sleep(3);




    }

}
