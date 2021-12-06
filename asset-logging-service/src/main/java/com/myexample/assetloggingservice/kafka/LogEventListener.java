package com.myexample.assetloggingservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogEventListener {
	
	@KafkaListener(topics = "topic_log", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void listener(@Payload String message, @Headers MessageHeaders headers) {

		log.info("Received log event : " + message);
	}
}
