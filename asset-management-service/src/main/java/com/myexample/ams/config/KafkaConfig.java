package com.myexample.ams.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaConfig {

	@Value(value = "${kafka.bootstrap.server}")
	private String KAFKA_SERVER_URL;

	@Value(value = "${kafka.connectAssetTopic}")
	private String TOPIC_CONNECT_ASSET;

	@Value(value = "${kafka.logTopic}")
	private String TOPIC_LOG;

	@Bean
	public NewTopic connectAssetTopic() {
		log.info("Creating topic {}" + TOPIC_CONNECT_ASSET);
		return new NewTopic(TOPIC_CONNECT_ASSET, 1, (short) 1);
	}

	@Bean
	public NewTopic logTopic() {
		log.info("Creating topic {}" + TOPIC_LOG);
		return new NewTopic(TOPIC_LOG, 1, (short) 1);
	}

	@Bean
	public ProducerFactory<String, String> kafkaProducerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL);
		configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, "10000");
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(kafkaProducerFactory());
	}

}
