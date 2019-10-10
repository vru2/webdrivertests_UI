package com.cleartrip.platform.email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailwithAttachments extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = Service_Url("EMAIL_ATTACHMENTS");
		resp=EmailPostAPI(params1,headersForEmailpostcall(), url);
		validation(resp);

 }
}
