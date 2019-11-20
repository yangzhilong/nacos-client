 package com.longge.nacos.dto;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author roger yang
 * @date 11/20/2019
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "config")
public class ConfigDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7125380285338470635L;

    private String name;
    
    private Integer age;
}
