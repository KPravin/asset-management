package com.myexample.acs.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Pravin.Kumar
 *
 */
@RestController
@RequestMapping("/asset-connect")
public class AssetConnectivityController {

	@GetMapping()
	public ResponseEntity<?> get() {
		return null;
	}
}
