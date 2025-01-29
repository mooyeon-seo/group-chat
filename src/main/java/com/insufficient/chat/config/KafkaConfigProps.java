package com.insufficient.chat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaConfigProps {
    private String topic;
}
