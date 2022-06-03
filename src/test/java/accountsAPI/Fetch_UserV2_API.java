// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Fetch_UserV2_API  extends AccountsCommon_API{
	
    @Test
	public void FetchUserV2() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("FetchUserV2", "");
    		validation( resp, "FetchUserV2", "");
    }
}