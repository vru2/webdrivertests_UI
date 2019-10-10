package com.cleartrip.platform.supportchatapi;

import org.testng.annotations.Test;
import io.restassured.response.Response;



public class SupportcreateChatAPI extends SupportAPICommon {
	@Test(groups={"Regression"})
	public void supportcreateChat() throws Exception{
		String url =Service_Url("Selfcare_createChat");
		Response resp;
		resp=createChat(headersForchat(),params, url);
		validation(resp);
	    
	}
}


