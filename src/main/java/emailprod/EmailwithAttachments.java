package emailprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailwithAttachments extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = "http://email-api.cltp.com:9001/email";
		resp=EmailPostAPI(params1,headersForEmailpostcall(), url);
		validation(resp);

 }
}
