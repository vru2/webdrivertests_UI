package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class DepositAccount_Search extends AccountsCommon_API
{
	
	 @Test
		public void depositAccount_search() throws IOException, JSONException{
	       		Response resp ;		
	      		resp =getCall("depositAccount_search", "");
	    		validation( resp, "depositAccount_search", "");
	    		
/*
	    		ResponseBody body = resp.getBody();
	    			System.out.println("Response of API is:" + body.asString());*/
	    }

}
