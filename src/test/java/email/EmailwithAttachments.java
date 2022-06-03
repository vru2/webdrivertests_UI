package test.java.  email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailwithAttachments extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = "http://172.17.51.86:8001/email";
		resp=EmailPostAPI(params1,headersForEmailpostcall(), url);
		System.out.println(resp.asString());
		validation(resp);

 }
}
