package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Userclassification_Health_Test_Url extends AccountsCommon_API
{
	
	@Test
	public void Userclassification_Health_Test_Url() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Userclassification_Health_Test_Url", "");
		validation( resp, "Userclassification_Health_Test_Url", "");
		
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		
	}

}


