package com.myexample.ams.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myexample.ams.model.AssetModel;

/**
 * 
 * @author Pravin.Kumar
 *
 */
@Service
public class ConnectionEventPublisher {

	@Value(value = "${kafka.connectAssetTopic}")
	private String TOPIC_CONNECT_ASSET;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(AssetModel message) throws JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String json = objectMapper.writeValueAsString(message);

		Message<String> messageJSON = MessageBuilder.withPayload(json).setHeader(KafkaHeaders.TOPIC, TOPIC_CONNECT_ASSET).build();
		kafkaTemplate.send(messageJSON);
	}
}
