package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Create_CompanyTags  extends AccountsCommon_API {
	
	

	@Test
	public void createCompanyTags() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("createCompanyTags", "");
		validation(resp, "createCompanyTags", "");
		

//		ResponseBody body = resp.getBody();
//			System.out.println("Response of API is:" + body.asString());

		}


}
