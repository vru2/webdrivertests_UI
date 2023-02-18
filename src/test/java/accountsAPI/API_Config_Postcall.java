package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_Config_Postcall extends AccountsCommon_API
{

	@Test
	public void Apiconfig_postcall() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("Apiconfig_postcall", "");
    		validation( resp, "Apiconfig_postcall", "");
    		/*
    		ResponseBody body = resp.getBody();
    		System.out.println("Response body:" +body.asString());*/
    		}

}
