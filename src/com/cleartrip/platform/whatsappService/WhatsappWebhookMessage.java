package com.cleartrip.platform.whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappWebhookMessage extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException {
		Response resp;
		String url = Service_Url("WHATSAPP_WEBHOOK_MESSAGE");
		resp=paramsForwhatsappwebhook(headersForWhatsappUserMessage(),params5,url);
		validateWebhook(resp);
		
	}

}
