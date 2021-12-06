package com.myexample.ams.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myexample.ams.model.AssetModel;
import com.myexample.ams.service.AssetService;

@RestController
@RequestMapping("/asset")
@CrossOrigin
public class AssetController {

	@Autowired
	private AssetService assetService;

	@PostMapping
	public ResponseEntity<?> postAsset(@RequestBody AssetModel assetModel) {

		try {
			return new ResponseEntity<>(assetService.createAsset(assetModel), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * 
	 */
	@GetMapping
	public ResponseEntity<?> getAllAssets() {
		try {
			return new ResponseEntity<>(assetService.getAllAssets(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		}
	}
}
