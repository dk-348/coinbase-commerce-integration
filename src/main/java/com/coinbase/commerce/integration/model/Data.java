package com.coinbase.commerce.integration.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Data {

	public Map<String, String> addresses;
	public String brand_color;
	public String brand_logo_url;
	public String cancel_url;
	public String code;
	public boolean coinbase_managed_merchant;
	public String created_at;
	public String description;
	public Map<String, String> exchange_rates;
	public String expires_at;
	public double fee_rate;
	public boolean fees_settled;
	public String hosted_url;
	public String id;
	public Map<String, String> local_exchange_rates;
	public String logo_url;
	public Map<String, String> metadata;
	public String name;
	public boolean offchain_eligible;
	public String organization_name;
	public PaymentThreshold payment_threshold;
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Object> payments;
	public Map<String, Map<String, String>> pricing;
	public String pricing_type;
	public boolean pwcb_only;
	public String redirect_url;
	public String resource;
	public String support_email;
	@JacksonXmlElementWrapper(useWrapping = false)
	public List<Map<String, String>> timeline;
	public boolean utxo;

}
