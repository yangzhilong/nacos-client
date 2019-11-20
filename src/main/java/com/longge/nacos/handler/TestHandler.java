 package com.longge.nacos.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author roger yang
 * @date 11/20/2019
 */
public class TestHandler {
    public static Map<String, String> fallbackGet() {
        Map<String, String> result = new HashMap<>();
        result.put("dto", "fallback");
        result.put("pro", "fallback");
        return result;
    }
    
    public static Map<String, String> blockGet() {
        Map<String, String> result = new HashMap<>();
        result.put("error", "too busy");
        return result;
    }
}
