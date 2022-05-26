package test.java.whatsappServiceProd;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappOptinFlowStatus extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException
	{
	Response resp;
	String url ="http://whatsappapi.cltp.com:9001/whatsapp/optinStatus";
	resp=paramsForwhatsappOptinStatus(headersForWhatsappUserMessage(),params4,url);
	validateOptinFlow(resp);
	}

}
