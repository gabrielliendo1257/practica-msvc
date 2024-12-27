package com.guille.springcloud.microservicio.item.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebclientConfiguration
 */
@Configuration
public class WebclientConfiguration {

    @Value(value = "${base.url.msvc-product}")
    private String url;

    @Bean
    @LoadBalanced
    WebClient.Builder webClient() {
        return WebClient.builder().baseUrl(url);
    }
}
