// Framework - Cleartrip Automation
// Author - Saloni

package paymentsAPI;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API_storeCard extends API_PaymentCommon
{	
	
	@Test(priority = 1, alwaysRun = true)
	public void Wallet_storeCard () throws Exception{
		Response resp ;		
		
		resp = walletEndPoints("wallet_storeCards","");	
		validation("wallet_getcards", resp);
			
	}
	
	
	@Test(priority = 2, alwaysRun = true)
	public void Wallet_validateCard () throws Exception{
		Response resp1 ;		
		resp1 = walletEndPoints("wallet_validateCards","");	
		validation("wallet_getcards", resp1);
			
	}
	
	@Test(priority = 3, alwaysRun = true)
	public void Wallet_getCard_DeleteCard () throws Exception{
		Response resp ;		
		String cardid = null;
		resp = walletEndPoints("wallet_getCards","");	
		validation("wallet_getcards", resp);
		
		JsonPath jsevaluator = resp.jsonPath();
		List cardids = jsevaluator.getList("id");
		List cardnumber = jsevaluator.getList("number");
		
		for (int i=0;i< cardids.size();i++)
		{
			if((cardnumber.get(i).toString()).equalsIgnoreCase("512345XXXXXX2346"))
			{
				cardid = cardids.get(i).toString();
				resp = walletEndPoints("wallet_deleteCards",cardid);	
				validation("wallet_deleteCards", resp);
			}
			
		}
				
	}
	
	
}
