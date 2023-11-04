package com.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 数据通信
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate getResttemplate(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate;
    }
}
