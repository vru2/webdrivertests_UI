package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Userclassification_Gettripdetails_fromtripid extends AccountsCommon_API
{
	@Test
	public void Userclassification_Gettripdetails_fromtripid() throws IOException, JSONException{
		Response resp ;		
		resp =getCall("Userclassification_Gettripdetails_fromtripid", "");
		validation( resp, "Userclassification_Gettripdetails_fromtripid", "");
		
		ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());
		
		
	}

}
