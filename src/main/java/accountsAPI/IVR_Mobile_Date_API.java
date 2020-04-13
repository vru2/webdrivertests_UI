// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;
import org.json.JSONException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class IVR_Mobile_Date_API  extends AccountsCommon_API{

    @Test
	public void IVR_Date() throws IOException, JSONException{
    	if(!common.value("host").equals("www")) {
      		Response resp ;		
      		resp =ivr("Date");
    		validation( resp, "Date", "");
    		 ResponseBody body = resp.getBody();
    		 
    	} else Reporter.log("Script will not execute in Production");
    	
    	
    }
}