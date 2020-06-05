package emailprod;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class EmailPost extends EmailCommon{
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url ="http://email-api.cltp.com:9001/email";
		resp=EmailPostAPI(params,headersForEmailpostcall(), url);
		validation(resp);
		
		
	}
	

}
