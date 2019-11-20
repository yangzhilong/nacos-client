 package com.longge.nacos.config;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;

import lombok.Getter;
import lombok.Setter;

/**
 * @author roger yang
 * @date 11/13/2019
 */
@Configuration
public class SentinelAutoConfiguration {
    @Autowired
    private SentinelNocasConfig config;
    
    @Bean
    public SentinelBootstrap sentinel() {
        Converter<String, List<FlowRule>> parser = source -> {
            return JSON.parseObject(source, new TypeReference<List<FlowRule>>() {});
        };
        
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, config.getServerAddr());
        if(StringUtils.isNotBlank(config.getNamespace())) {
            properties.put(PropertyKeyConst.NAMESPACE, config.getNamespace());
        }
        
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, config.getGroupId(), config.getDataId(), parser);
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        return new SentinelBootstrap();
    }
    
    @Configuration
    @ConfigurationProperties(prefix = "sentinel.nocas")
    @Getter
    @Setter
    static class SentinelNocasConfig implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 4327218038635420364L;
        @NotBlank
        private String serverAddr;
        @NotBlank
        private String groupId;
        @NotBlank
        private String dataId;
        
        private String namespace;
    }
    
    static class SentinelBootstrap {} 
}
