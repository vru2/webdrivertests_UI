package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Userclassification_Parsing_CSV extends  AccountsCommon_API 
{
	@Test
	public void signupV1() throws IOException,JSONException
	{
		Response resp;
		resp = postCall("Userclassification_Parsing_CSV","");
		
		validation(resp,"Userclassification_Parsing_CSV","");
				
	}
	


}
