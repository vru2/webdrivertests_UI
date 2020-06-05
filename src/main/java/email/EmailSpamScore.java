package email;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailSpamScore extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = Service_Url("EMAIL_SPAMSCORE");
		resp=Emailspamscore(params,headersForEmailpostcall(),url);
		validationSpamScore(resp);
		
	}
		

}
