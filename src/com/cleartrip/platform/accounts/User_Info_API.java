// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.accounts;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class User_Info_API  extends AccountsCommon_API{
	
    @Test
	public void UserInfo() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("UserInfo", "");
    		validation( resp, "UserInfo", "");
    }
}