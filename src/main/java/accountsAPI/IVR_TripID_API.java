// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class IVR_TripID_API  extends AccountsCommon_API{

    @Test
	public void IVR_Trip() throws IOException, JSONException{
    	if(!common.value("host").equals("www")) {
      		Response resp ;		
      		resp =ivr("TripID");
    		validation( resp, "TripID", "");
    	} else Reporter.log("Script will not execute in Production");
    }
}