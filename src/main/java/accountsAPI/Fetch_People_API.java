// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Fetch_People_API  extends AccountsCommon_API{
	
    @Test
	public void FetchPeople() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("FetchPeople", "");
    		validation( resp, "FetchPeople", "");
    }
}