package com.education.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Andrey Kryukov
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
