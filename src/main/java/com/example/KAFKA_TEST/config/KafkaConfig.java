package com.example.KAFKA_TEST.config;

import com.example.KAFKA_TEST.dto.UserVo;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String BUILD_ORDER_TOPIC = "BUILD-ORDER-TOPIC";
    public static final String NOTICE_TOPIC = "NOTICE-TOPIC";
    public static final String DEFAULT_SERVER = "127.0.0.1:9092";

    @Bean
    public ProducerFactory<String, UserVo> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, String> producerStrFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_SERVER);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean(name = "kafkaTemplateJson")
    public KafkaTemplate<String, UserVo> kafkaTemplateJson() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean(name = "kafkaTemplateStr")
    public KafkaTemplate<String, String> kafkaTemplateStr() {
        return new KafkaTemplate<>(producerStrFactory());
    }

    @Bean
    public NewTopic buildOrderTopic() {
        return TopicBuilder.name(BUILD_ORDER_TOPIC).build();
    }

    @Bean
    public NewTopic noticeTopic() {
        return TopicBuilder.name(NOTICE_TOPIC).build();
    }
}
