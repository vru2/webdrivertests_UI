package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Account_Service_Company_AddGSTwith_CompanyID extends AccountsCommon_API

{
	@Test
	public void companygst() throws IOException, JSONException{

		Response resp ;		
		resp =postCall("Account_Service_Company_AddGSTwith_CompanyID", "");
		validation_Linkdepositaccount( resp, "Account_Service_Company_AddGSTwith_CompanyID", "");
		
	/*	ResponseBody body = resp.getBody();
		System.out.println("Response of API is:" + body.asString());*/	
		

	}
}
