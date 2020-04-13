package accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Deposit_Account_Walletbalance_Check extends AccountsCommon_API
{
	 @Test
		public void depositAccount_walletbalance_check() throws IOException, JSONException{
	       		Response resp ;		
	      		resp =getCall("depositAccount_walletbalance_check", "");
	    		validation( resp, "depositAccount_walletbalance_check", "");
	    		

	    	/*	ResponseBody body = resp.getBody();
	    			System.out.println("Response of API is:" + body.asString());*/
	    }


}
