package accountsAPI;
import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Signin_Postcall extends AccountsCommon_API 
{

	@Test
	public void signinpostcall() throws IOException, JSONException{
		Response resp ;		
		resp =postCall("signinpostcall", "");
		validation( resp, "signinpostcall", "");
		
		
	}


}
