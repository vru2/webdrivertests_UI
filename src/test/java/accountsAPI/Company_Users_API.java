// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Company_Users_API  extends AccountsCommon_API{
	
    @Test
	public void CompanyUsers() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("CompanyUsers", "");
    		validation( resp, "CompanyUsers", "");
    }
}