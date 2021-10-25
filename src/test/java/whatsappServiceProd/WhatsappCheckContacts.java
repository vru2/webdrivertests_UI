package whatsappServiceProd;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappCheckContacts extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void checkContacts() throws IOException {
		Response resp;
		String url = "http://whatsappapi.cltp.com:9001/whatsapp/checkContacts";
		resp=paramsForwhatsappservice(headersForWhatsAppCheckContacts(),params,url);
		System.out.println(resp.asString());
		validation(resp);
	}

}
