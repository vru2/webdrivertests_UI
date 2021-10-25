// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Fetch_Bank_API  extends AccountsCommon_API{
	
    @Test
	public void FetchBank() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("FetchBank", "");
    		validation( resp, "FetchBank", "");
    }
}