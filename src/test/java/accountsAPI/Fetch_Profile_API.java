// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Fetch_Profile_API  extends AccountsCommon_API{
	
    @Test
	public void FetchProfile() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("FetchProfile", "");
    		validation( resp, "FetchProfile", "");
    }
}