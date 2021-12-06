package com.myexample.ams.model;

import java.util.UUID;

public class AssetModel {

	private UUID assetId;
	private String assetName;
	private String description;

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

	@Override
	public String toString() {
		return "AssetModel [assetId=" + assetId + ", assetName=" + assetName + ", description=" + description + "]";
	}

}
