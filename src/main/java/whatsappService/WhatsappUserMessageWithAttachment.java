package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappUserMessageWithAttachment extends WhatsappCommon{
	@Test(groups="Regression")
	public void message() throws IOException {
		Response resp;
		String url = "http://172.17.51.86:8227/whatsapp/message";
	
	    resp=paramsForwhatsappservicewithattachment(headersForWhatsappUserMessage(),params2,url);
	    validateMessage(resp);

	}

}
