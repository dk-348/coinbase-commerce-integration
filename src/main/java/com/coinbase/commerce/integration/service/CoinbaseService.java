package com.coinbase.commerce.integration.service;

import java.util.Map;

import org.apache.commons.codec.digest.HmacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coinbase.commerce.integration.model.ChargeRequest;
import com.coinbase.commerce.integration.model.ChargeResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoinbaseService {

	private static final Logger logger = LoggerFactory.getLogger(CoinbaseService.class);

	private static final String COINBASE_API_URL = "https://api.commerce.coinbase.com/charges";

	private final RestTemplate restTemplate;

	@Value("${coinbase.api.key}")
	private String apiKey;
	
	@Value("${coinbase.shared.secret}")
	private String secretKey;

	public CoinbaseService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public ChargeResponse createCharge(ChargeRequest chargeRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-CC-Api-Key", apiKey);
		headers.set("X-CC-Version", "2018-03-22");

		HttpEntity<ChargeRequest> request = new HttpEntity<>(chargeRequest, headers);

		try {
			logger.info("JSON payload: {}", new ObjectMapper().writeValueAsString(chargeRequest));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResponseEntity<ChargeResponse> response = restTemplate.exchange(COINBASE_API_URL, HttpMethod.POST, request,
				ChargeResponse.class);

		return response.getBody();
	}
	


    public void processWebhookNotification(String payload) {
        // Parse the payload and extract the necessary information
        Map<String, Object> notificationData = parseWebhookNotification(payload);
        
        // get the status
        Map<String, Object> eventObj = (Map<String, Object>) notificationData.get("event");
        String status = (String) eventObj.get("type");
        if ("charge:confirmed".equals(status)) {
            // Payment is successful, log the notification
        	logger.info("================================================");
        	logger.info("================================================");
            logger.info("Payment successful - Notification: {}", payload);
            logger.info("================================================");
            logger.info("================================================");
        } else {
            // For other statuses, you can handle them as needed
            // ...
        	
        	logger.error("================================================");
        	logger.error("================================================");
            logger.error("status {} - Notification: {}", status, payload);
            logger.error("================================================");
            logger.error("================================================");

        }
    }

    private Map<String, Object> parseWebhookNotification(String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            // Handle parsing exception
            logger.error("Failed to parse webhook notification: {}", payload);
            throw new RuntimeException("Failed to parse webhook notification.", e);
        }
    }
    
    public boolean verifySignature(String payload, String signature) {
        String computedSignature = computeSignature(payload);
        return computedSignature.equals(signature);
    }

    @SuppressWarnings("deprecation")
	private String computeSignature(String payload) {
        return HmacUtils.hmacSha256Hex(secretKey, payload);
    }
	
}
