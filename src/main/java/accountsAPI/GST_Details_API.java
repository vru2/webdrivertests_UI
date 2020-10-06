// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GST_Details_API  extends AccountsCommon_API{
	
    @Test
	public void get_gstdetails() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("GstDetails", "");
    		validation( resp, "GstDetails", "");
    }
}