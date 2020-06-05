package email;

import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class EmailPost extends EmailCommon{
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = Service_Url("EMAILS_POST_CALL");
		resp=EmailPostAPI(params,headersForEmailpostcall(), url);
		validation(resp);
		
		
	}
	

}
