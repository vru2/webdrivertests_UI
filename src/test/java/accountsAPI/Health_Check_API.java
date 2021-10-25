// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Health_Check_API  extends AccountsCommon_API{
	
    @Test
	public void health_Check() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("healthCheck", "");
    		validation( resp, "", "");
    }
}