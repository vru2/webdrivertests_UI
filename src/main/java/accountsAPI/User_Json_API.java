// Framework - Cleartrip Automation
// Author - Kiran Kumar

package accountsAPI;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class User_Json_API  extends AccountsCommon_API{
	
    @Test
	public void UserJson() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("UserJson", "");
    		validation( resp, "UserJson", "");
    }
}