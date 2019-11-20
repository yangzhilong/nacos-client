 package com.longge.nacos.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longge.nacos.dto.ConfigDto;

/**
 * @author roger yang
 * @date 11/20/2019
 */
@RefreshScope
@RestController
@RequestMapping("/test")
public class TestRest {
    
    @Value("${common.name:default}")
    private String name;
    @Resource
    private ConfigDto configDto;
    
    @GetMapping("/get")
    public Map<String, String> get() {
        Map<String, String> result = new HashMap<>();
        result.put("dto", configDto.getName());
        result.put("pro", name);
        return result;
    }
}
