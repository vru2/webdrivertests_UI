// Framework - Cleartrip Automation
// Author - Sphurti

package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GST_Details_POST_API extends AccountsCommon_API {
	
	@Test
	public void createUpdateGST() throws IOException, JSONException{	
		Response resp ;		
  		resp =postCall("createUpdateGST", "");
		validation( resp, "createUpdateGST", "");
	}

}
