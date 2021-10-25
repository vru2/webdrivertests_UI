package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ForgotPasswordEmail_API extends AccountsCommon_API {
	@Test
	public void AccountRegister() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("ForgotPasswordEmail", "");
    		validation( resp, "ForgotPasswordEmail", "");
    }

}
