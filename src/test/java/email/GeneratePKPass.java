package test.java.  email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePKPass extends EmailCommon {
	@Test(groups={"Regression"})		
	public void generatePKPass() throws IOException {
		Response resp;
		String url = "http://172.17.51.86:8001/email/getPkPass";
		resp=EmailgeneratePKPass(params3,headersForEmailgeneratePKPasscall(),url);
		System.out.println(resp.asString());
		validationPKPass(resp);
	}

}
