package com.coinbase.commerce.integration.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "charge")
@JsonRootName("charge")
public class ChargeRequest {

	public String name;
	public String description;
	public Map<String, String> local_price;
	public String pricing_type;
	public Map<String, String> metadata;
	public String redirect_url;
	public String cancel_url;

}
