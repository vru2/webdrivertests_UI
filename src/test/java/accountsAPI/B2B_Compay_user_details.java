package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class B2B_Compay_user_details extends AccountsCommon_API{
	
    @Test
	public void b2bCmpuserdetails() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("b2bcmpuserdetails", "");
      		validation( resp, "b2bcmpuserdetails", "");
    		
    }



}
