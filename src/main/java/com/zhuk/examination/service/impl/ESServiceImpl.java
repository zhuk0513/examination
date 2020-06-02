package com.zhuk.examination.service.impl;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuk
 * @description: 1
 * @date 2020-06-01
 */
@Service
public class ESServiceImpl {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    public void findByParams(){
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("name", "zhangsan"));
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        searchRequest.indices("demo");
        searchRequest.source(sourceBuilder);

    }

    public void add() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name","李四");
        jsonMap.put("text","长得很帅");
        IndexRequest indexRequest = new IndexRequest("demo")
                .id(UUID.randomUUID().toString()).source(jsonMap);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

}
