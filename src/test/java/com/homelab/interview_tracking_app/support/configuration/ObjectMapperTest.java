package com.homelab.interview_tracking_app.support.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;

@TestConfiguration
public class ObjectMapperTest {

    @Bean
    public ObjectMapper objectMapperTest() {
        return new ObjectMapper();
    }
}
