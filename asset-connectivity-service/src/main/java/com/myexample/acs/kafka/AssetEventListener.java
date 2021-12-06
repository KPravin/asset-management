package com.myexample.acs.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AssetEventListener {

	@Autowired
	private LogEventPublisher logEventPublisher;

	@KafkaListener(topics = "topic_connect_asset", groupId = "group_id", containerFactory = "kafkaListenerContainerFactory")
	public void listener(@Payload String message, @Headers MessageHeaders headers) {

		log.info("Received connection event : " + message);

		String initiateConnection = "Initiating connection to asset: " + message;
		log.info(initiateConnection);
		logEventPublisher.sendMessage(initiateConnection);

		// TODO: Try connecting to asset

		// TODO: If connection successful send "successful connection" message to "log" topic
		String success = "Successfully connected to asset: " + message;
		log.info(success);
		logEventPublisher.sendMessage(success);

		// TODO: If connection fails, send "failed connection" message to "log" topic
		//String failure = "Failure connecting to asset: " + message;
		//log.info(failure);
		//logEventPublisher.sendMessage(failure);
	}
}
