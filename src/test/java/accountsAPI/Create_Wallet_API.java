// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.accountsAPI;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Create_Wallet_API  extends AccountsCommon_API{
	
    @Test
	public void CreateWallet() throws IOException, JSONException{
       		Response resp ;		
      		resp =postCall("CreateWallet", "");
    		validation( resp, "CreateWallet", "");
    }
}