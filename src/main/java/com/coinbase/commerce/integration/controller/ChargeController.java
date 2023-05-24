package com.coinbase.commerce.integration.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@PostMapping(value = "create", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ChargeResponse createCharge(@RequestBody ChargeRequest chargeRequest) {

		return coinbaseService.createCharge(chargeRequest);
	}

	@PostMapping(value = "webhook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> handleWebhookNotification(HttpServletRequest request, @RequestBody String payload) {
		// Extract the signature from the request headers
		String signature = request.getHeader("X-CC-Webhook-Signature");

		// Verify the signature
		if (coinbaseService.verifySignature(payload, signature)) {
			// Signature is valid, process the webhook notification
			coinbaseService.processWebhookNotification(payload);
			return ResponseEntity.ok("Webhook notification processed successfully.");
		} else {
			// Signature is invalid, ignore the webhook notification or handle it as needed
			return ResponseEntity.badRequest().body("Invalid signature.");
		}
	}

}
