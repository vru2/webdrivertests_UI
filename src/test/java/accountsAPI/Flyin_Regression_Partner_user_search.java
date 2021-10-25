package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Flyin_Regression_Partner_user_search extends AccountsCommon_API
{
	@Test
	public void flyinusersearchV2() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("flyinusersearchV2_Flyinregression", "");
		validation( resp, "flyinusersearchV2_Flyinregression", "");


		/*ResponseBody body = resp.getBody();
			System.out.println("Response of API is:" + body.asString());*/
			
		
	}
}
