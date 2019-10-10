// Framework - Cleartrip Automation
// Author - Kiran Kumar

package com.cleartrip.platform.accounts;

import java.io.IOException;
import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Deposit_Acct_Search_API  extends AccountsCommon_API{
	
    @Test
	public void DepositAcctSearch() throws IOException, JSONException{
       		Response resp ;		
      		resp =getCall("DepositAcctSearch", "");
    		validation( resp, "DepositAcctSearch", "");
    }
}