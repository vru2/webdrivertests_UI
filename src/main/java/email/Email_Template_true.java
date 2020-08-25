package email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Email_Template_true extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = "http://172.17.26.11:8001/email";
		resp=EmailPostAPI(params2,headersForEmailpostcall(), url);
		validation(resp);
	}

}
