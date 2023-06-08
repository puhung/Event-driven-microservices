package com.microservices.demo.kafka.admin.config;

import com.microservices.demo.config.KafkaConfigData;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRetry
public class KafkaAdminConfig {
    private final KafkaConfigData kafkaConfigData;

    public KafkaAdminConfig(KafkaConfigData configData) {
        this.kafkaConfigData = configData;
    }

    @Bean
    public AdminClient adminClient() {
        Map<String, Object> config = new HashMap<>();
        config.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers());
        return AdminClient.create(config);
    }
}
