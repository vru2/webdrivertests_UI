package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappOptinFlow extends WhatsappCommon{
	@Test(groups={"Regression"})
	public void message() throws IOException
	{
		Response resp;
		//String url = "http://whatsappapi.cltp.com:9001/r3/whatsapp/optin";
		String url ="http://172.17.51.86:8227/whatsapp/optin";
		resp=paramsForwhatsappOptin(headersForWhatsappUserMessage(),params3,url);
		validateOptinFlow(resp);
		
	}

}
