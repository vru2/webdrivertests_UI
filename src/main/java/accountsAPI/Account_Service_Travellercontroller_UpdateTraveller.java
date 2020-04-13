package accountsAPI;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.StatusCode;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_Travellercontroller_UpdateTraveller  extends AccountsCommon_API
{

	@Test
	public void travellercontroller_updatetraveller() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("travellercontroller_updatetraveller", "");
		validation( resp, "travellercontroller_updatetraveller", "");

	/*	
		
		ResponseBody body = resp.getBody();
			System.out.println("Response of API is:" + body.asString());
*/
		
			
	}
}
