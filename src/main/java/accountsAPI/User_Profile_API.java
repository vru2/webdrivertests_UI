// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class User_Profile_API  extends AccountsCommon_API{

    @Test
	public void Userprofile() throws IOException, JSONException{
    	if(!common.value("host").equals("www")) {
      		Response resp ;		
      		resp =getCall("UserJson", "");
    		validation( resp, "UserJson", "");
    	} else Reporter.log("Script will not execute in Production");
    }
}