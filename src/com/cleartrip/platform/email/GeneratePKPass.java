package com.cleartrip.platform.email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePKPass extends EmailCommon {
	@Test(groups={"Regression"})		
	public void generatePKPass() throws IOException {
		Response resp;
		String url = Service_Url("EMAIL_GENERATEPKPASS");
		resp=EmailgeneratePKPass(params3,headersForEmailgeneratePKPasscall(),url);
		validationPKPass(resp);
	}

}
