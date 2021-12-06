package com.myexample.acs.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.myexample.acs.model.LogModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogEventPublisher {

	@Value(value = "${kafka.logTopic}")
	private String TOPIC_LOG;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {

		LogModel log = new LogModel();
		log.setDateTime(new Date());
		log.setLog(message);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		String json = null;
		try {
			json = objectMapper.writeValueAsString(log);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		Message<String> messageJSON = MessageBuilder.withPayload(json).setHeader(KafkaHeaders.TOPIC, TOPIC_LOG).build();
		kafkaTemplate.send(messageJSON);
	}
}
