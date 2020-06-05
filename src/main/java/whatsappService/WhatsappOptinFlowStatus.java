package whatsappService;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class WhatsappOptinFlowStatus extends WhatsappCommon {
	@Test(groups={"Regression"})
	public void message() throws IOException
	{
	Response resp;
	String url = Service_Url("WHATSAPP_OPTIN_STATUS");
	resp=paramsForwhatsappOptinStatus(headersForWhatsappUserMessage(),params4,url);
	validateOptinFlow(resp);
	}

}
