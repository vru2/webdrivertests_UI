// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.accounts;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Fetch_Company_API  extends AccountsCommon_API{
	
    @Test
	public void FetchCompany() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("FetchCompany", "");
    		validation( resp, "FetchCompany", "");
    }
}