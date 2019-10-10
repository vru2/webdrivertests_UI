package com.cleartrip.platform.accounts;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Feedback_email extends AccountsCommon_API
{

	 @Test
		public void Feedbackemail() throws IOException, JSONException{
	       		Response resp ;		
	      		resp =postCall("Feedbackemail", "");
	    		validation( resp, "Feedbackemail", "");
	    }
	
}