package emailprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GeneratePKPass extends EmailCommon {
	@Test(groups={"Regression"})		
	public void generatePKPass() throws IOException {
		Response resp;
		String url = "http://emailapi.cltp.com:9001/email/getPkPass";
		resp=EmailgeneratePKPass(params3,headersForEmailgeneratePKPasscall(),url);
		validationPKPass(resp);
	}

}
