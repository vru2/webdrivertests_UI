package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappOptinFlowStatus extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException
	{
	Response resp;
	String url = "http://172.17.26.11:8227/whatsapp/optinStatus";
	resp=paramsForwhatsappOptinStatus(headersForWhatsappUserMessage(),params4,url);
	validateOptinFlow(resp);
	}

}
