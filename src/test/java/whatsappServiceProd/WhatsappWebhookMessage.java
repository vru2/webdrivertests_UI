package test.java.whatsappServiceProd;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappWebhookMessage extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException {
		Response resp;
		String url ="http://whatsappapi.cltp.com:9001/whatsapp/webhook/messages/cleartrip";
		resp=paramsForwhatsappwebhook(headersForWhatsappUserMessage(),params5,url);
		validateWebhook(resp);
		
	}

}
