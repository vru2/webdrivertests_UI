// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Role_Info_API  extends AccountsCommon_API{
	
    @Test
	public void RoleInfo() throws IOException, JSONException{
       		Response resp ;		
      		resp = postCall("RoleInfo", "");
    		validation( resp, "RoleInfo", "");
    }
}