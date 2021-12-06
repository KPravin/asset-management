package com.myexample.ams.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.myexample.ams.model.AssetModel;

@Repository
public class AssetRepository {

	private static List<AssetModel> assetDatabase = new ArrayList<>();

	public AssetModel addAsset(AssetModel assetModel) {
		assetModel.setAssetId(UUID.randomUUID());
		assetDatabase.add(assetModel);
		return assetModel;
	}

	public AssetModel getAssetById(UUID assetId) {
		return this.assetDatabase.stream().filter(asset -> assetId == asset.getAssetId()).findAny().orElse(null);
	}

	public List<AssetModel> getAll() {
		return this.assetDatabase;
	}

	public AssetModel updateAsset(AssetModel assetModel) {
		removeAsset(assetModel.getAssetId());
		return addAsset(assetModel);
	}

	public boolean removeAsset(UUID assetId) {
		AssetModel assetToBeRemoved = getAssetById(assetId);
		if (assetToBeRemoved != null)
			this.assetDatabase.remove(assetToBeRemoved);

		return true;
	}
}
