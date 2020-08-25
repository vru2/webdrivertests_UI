package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappCheckContacts extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void checkContacts() throws IOException {
		Response resp;
		String url = "http://172.17.26.11:8227/whatsapp/checkContacts";
		resp=paramsForwhatsappservice(headersForWhatsAppCheckContacts(),params,url);
		System.out.println(resp.asString());
		validation(resp);
	}

}
