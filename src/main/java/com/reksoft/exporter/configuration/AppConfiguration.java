package com.reksoft.exporter.configuration;

import com.reksoft.exporter.properties.ApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
@EnableConfigurationProperties(ApiProperties.class)
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
