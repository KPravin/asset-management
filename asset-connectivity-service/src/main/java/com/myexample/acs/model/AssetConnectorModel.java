package com.myexample.acs.model;

import java.util.UUID;

public class AssetConnectorModel {

	private UUID id;
	private UUID assetId;
	private String assetName;
	private String description;
	private String status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getAssetId() {
		return assetId;
	}

	public void setAssetId(UUID assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
