// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Generate_Key_API  extends AccountsCommon_API{
	
    @Test
	public void GenerateAPIKEY() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("GenerateAPIKEY", "");
    		validation( resp, "GenerateAPIKEY", "");
    }
}