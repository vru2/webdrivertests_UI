// Framework - Cleartrip Automation
// Author - Sphurti

package com.cleartrip.platform.accounts;

import java.io.IOException;

import org.json.JSONException;
import org.testng.annotations.Test;

import com.cleartrip.platform.accounts.AccountsCommon_API;

import io.restassured.response.Response;

public class Wallet_Bal_Mob_API extends AccountsCommon_API {
	
	 @Test
	 public void fetchWallet() throws IOException, JSONException{
		Response resp ;		
   		resp = getCall("fetchWallet", "");
 		validation( resp, "fetchWallet", "");
	 }
}
