package com.coinbase.commerce.integration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ChargeResponse {

	@JsonProperty("data")
	@JacksonXmlProperty(localName = "data")
	private Data data;

}
