package test.java.emailprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailSpamScore extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = "http://email-api.cltp.com:9001/check-spam-score";
		resp=Emailspamscore(params,headersForEmailpostcall(),url);
		validationSpamScore(resp);
		
	}
		

}
