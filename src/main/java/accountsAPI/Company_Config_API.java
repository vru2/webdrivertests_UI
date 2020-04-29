// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Company_Config_API  extends AccountsCommon_API{
	
    @Test
	public void CmpConfig() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("CmpConfig", "");
    		validation( resp, "CmpConfig", "");
    }
}