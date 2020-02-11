package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class WhatsappMultilanguageSupport extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException {
		Response resp;
		String url = Service_Url("WHATSAPP_MESSAGE");
       resp=paramsForwhatsappmultilanguage(headersForWhatsappUserMessage(),params6,url);
       validateMessage(resp);
 }
}