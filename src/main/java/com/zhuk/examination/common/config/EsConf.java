package com.zhuk.examination.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuk
 * @description: es 配置
 * @date 2020-05-29
 */
@Configuration
public class EsConf {
/*    @Bean
    RestHighLevelClient elasticsearchClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("10.168.7.113", 9201, "http"),
                        new HttpHost("10.168.7.113", 9202, "http")));
        return client;
    }*/

}
