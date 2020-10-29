// Framework - Cleartrip Automation
// Author - Sphurti

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CreateUpdateTraveller_API extends AccountsCommon_API {
	
	@Test
	public void createUpdateTraveller() throws IOException, JSONException{
		
		Response resp ;		
  		resp = postCall("createUpdateTraveller", "");
		validation(resp, "createUpdateTraveller", "");
		
		}
	
	
}
