package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Company_Jsonfetch extends AccountsCommon_API{
	
    @Test
	public void Cmpjsonfetch() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("Cmpjsonfetch", "");
      		validation( resp, "Cmpjsonfetch", "");    		
    		//ResponseBody body = resp.getBody();
    }

}
