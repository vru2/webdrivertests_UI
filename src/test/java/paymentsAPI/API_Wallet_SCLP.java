// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.hamcrest.*;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class API_Wallet_SCLP extends API_PaymentCommon1
{
	@Test
	public void Wallet_GETWALLET_Details_UI()  {
		Response resp ;		
		resp = rearchWallet("GETWALLET_Details_UI","");
		validation("GETWALLET_Details_UI", resp);
		}

	@Test
	public void Wallet_GETWALLET_Trns_UI()  {
		Response resp ;
		resp = rearchWallet("GETWALLET_Trnx_UI","");
		validation("GETWALLET_Trnx_UI", resp);
	}

	@Test
	public void Wallet_GET_RateRule_SCLP_CLEMI()  {
		Response resp ;
		resp = payGet("GET_Raterule_CLEMI","");
		validation("GET_Raterule_CLEMI", resp);
	}

	@Test
	public void Wallet_GET_RateRule_SCLP_PL()  {
		Response resp ;
		resp = payGet("GET_Raterule_PL","");
		validation("GET_Raterule_PL", resp);
	}

}