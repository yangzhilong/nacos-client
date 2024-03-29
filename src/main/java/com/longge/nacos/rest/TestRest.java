 package com.longge.nacos.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.longge.nacos.dto.ConfigDto;
import com.longge.nacos.handler.TestHandler;

/**
 * @author roger yang
 * @date 11/20/2019
 */
@RefreshScope
@RestController
public class TestRest {
    
    @Value("${common.name:default}")
    private String name;
    @Resource
    private ConfigDto configDto;
    
    @GetMapping("/get")
    @SentinelResource(value = "test", blockHandlerClass = TestHandler.class, blockHandler = "blockGet", fallbackClass = TestHandler.class ,fallback = "fallbackGet")
    public Map<String, String> get() {
        Map<String, String> result = new HashMap<>();
        result.put("dto", configDto.getName());
        result.put("pro", name);
        try {
            Thread.sleep(RandomUtils.nextLong(1L, 1000L));
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
        int val = RandomUtils.nextInt(1, 2);
        if(2 == val) {
            throw new RuntimeException("val is 2");
        }
        return result;
    }
}
