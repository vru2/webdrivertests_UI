package com.cleartrip.platform.supportchatapi;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class SupportGatewaycreateChatAPI extends SupportAPICommon {
	@Test(groups={"Regression"})
	public void supportGatewaycreateChat() throws IOException{
	Response resp;
	String url=Service_Url("Selfcare_gatewayChat");
	resp=gatewaycreateChat(headersForchat(),params, url);
	validation(resp);
	}
}
