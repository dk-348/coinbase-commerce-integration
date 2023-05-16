package com.coinbase.commerce.integration.model;

import java.util.Map;

public class PaymentThreshold {

	public Map<String, String> overpayment_absolute_threshold;
	public String overpayment_relative_threshold;
	public Map<String, String> underpayment_absolute_threshold;
	public String underpayment_relative_threshold;

}
