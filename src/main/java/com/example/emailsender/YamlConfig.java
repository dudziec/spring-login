package com.example.emailsender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YamlConfig {
    @Value("${server.port}")
    private String serverPort; 
    @Value("${spring.datasource.url}")
    private String url;

    public String getServerPort() {
        return this.serverPort;
    }

    public String getUrl() {
        return this.url;
    }
}
