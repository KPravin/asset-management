package com.myexample.ams.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myexample.ams.kafka.ConnectionEventPublisher;
import com.myexample.ams.kafka.LogEventPublisher;
import com.myexample.ams.model.AssetModel;
import com.myexample.ams.repository.AssetRepository;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.Logger;

@Service
@Slf4j
public class AssetService {

	@Autowired
	private LogEventPublisher logEventPublisher;

	@Autowired
	private ConnectionEventPublisher connectionEventPublisher;
	
	@Autowired
	private AssetRepository assetRepository;
	
	public AssetModel createAsset(AssetModel assetModel) {

		assetModel.setAssetId(UUID.randomUUID());

		// Creating asset.
		String createAssetMessage = "Creating asset: " + assetModel;
		log.info(createAssetMessage);

		try {
			logEventPublisher.sendMessage(createAssetMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		assetRepository.addAsset(assetModel);

		// Asset creation successful
		String createAssetSuccess = "Asset created: " + assetModel;
		log.info(createAssetSuccess);

		try {
			logEventPublisher.sendMessage(createAssetSuccess);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// Asset creation successful
		String connectMessage = "Sending message to connectivity service to connect asset: " + assetModel;
		log.info(connectMessage);

		try {
			logEventPublisher.sendMessage(connectMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		// send message to connectivity service
		try {
			connectionEventPublisher.sendMessage(assetModel);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return assetModel;
	}
	
	public List<AssetModel> getAllAssets() {
		return assetRepository.getAll();
	}
}
