package test.java.  whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappOptinFlow extends WhatsappCommon{
	@Test(groups={"Regression"})
	public void message() throws IOException
	{
		Response resp;
		String url ="http://whatsappapi.cltp.com:9001/whatsapp/optin";
		resp=paramsForwhatsappOptin(headersForWhatsappUserMessage(),params3,url);
		validateOptinFlow(resp);
		
	}

}
