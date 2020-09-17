package email;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class EmailSpamScore extends EmailCommon {
	@Test(groups={"Regression"})		
	public void message() throws IOException {
		Response resp;
		String url = "http://172.17.51.86:8001/check-spam-score";
		resp=Emailspamscore(params,headersForEmailpostcall(),url);
		validationSpamScore(resp);
		
	}
		

}
