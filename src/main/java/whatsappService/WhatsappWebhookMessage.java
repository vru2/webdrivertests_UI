package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappWebhookMessage extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException {
		Response resp;
		String url = "http://172.17.26.11:8227/whatsapp/webhook/messages/cleartrip";
		resp=paramsForwhatsappwebhook(headersForWhatsappUserMessage(),params5,url);
		validateWebhook(resp);
		
	}

}
