// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.  accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Person_Json_API  extends AccountsCommon_API{

    @Test
	public void PersonJson() throws IOException, JSONException{
    	if(!common.value("host").equals("www")) {
      		Response resp ;		
      		resp =getCall("PersonJson","");
    		validation( resp, "PersonJson", "");
    	} else Reporter.log("Script will not execute in Production");
    }
}