package com.coinbase.commerce.integration.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coinbase.commerce.integration.model.ChargeRequest;
import com.coinbase.commerce.integration.model.ChargeResponse;
import com.coinbase.commerce.integration.service.CoinbaseService;

@RestController
@RequestMapping("/charge")
public class ChargeController {

	private final CoinbaseService coinbaseService;

	public ChargeController(CoinbaseService coinbaseService) {
		this.coinbaseService = coinbaseService;
	}

	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ChargeResponse createCharge(@RequestBody ChargeRequest chargeRequest) {

		return coinbaseService.createCharge(chargeRequest);
	}

}
