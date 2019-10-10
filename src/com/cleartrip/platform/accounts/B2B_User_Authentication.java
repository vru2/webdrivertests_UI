package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class B2B_User_Authentication extends AccountsCommon_API
{
	@Test
	public void B2B_user_authentication() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("B2B_user_authentication", "");
    		validation( resp, "B2B_user_authentication", "");
    }

}
