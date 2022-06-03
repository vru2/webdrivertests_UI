package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Userclassification_Userdetails_emailid  extends AccountsCommon_API

{

	@Test
	public void Userclassification_Userdetails_emailid() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Userclassification_Userdetails_emailid", "");
		validation( resp, "Userclassification_Userdetails_emailid", "");
		
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		
	}
}
