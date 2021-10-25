package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Create_updatetraveller_bookstep2 extends AccountsCommon_API {
	
	@Test
	public void createUpdateTraveller_bookstep2() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("createUpdateTraveller_bookstep2", "");
		validation(resp, "createUpdateTraveller_bookstep2", "");
		}
	
}
