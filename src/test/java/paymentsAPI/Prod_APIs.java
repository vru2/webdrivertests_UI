package paymentsAPI;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Prod_APIs extends API_PaymentCommon1 {

	@Test(priority=1)
	public void getActiveNB() {
		Response resp = prodAPIs("ActiveNB", "");
		validation_Prod("ActiveNB", resp);
	}
	
	@Test(priority=2)
	public void getPaymentStatus() {
		Response resp = prodAPIs("GetPaymentStatus", "");
		validation_Prod("GetPaymentStatus", resp);
	}
	
	@Test(priority=3)
	public void getThirdPartyWallets() {
		Response resp = prodAPIs("Pay_Get_TW_Wallets", "");
		validation_Prod("Pay_Get_TW_Wallets", resp);
	}
	
	@Test(priority=4)
	public void getActiveCardTypes() {
		Response resp = prodAPIs("ActiveCardTypes" , "");
		validation_Prod("ActiveCardTypes", resp);
	}

}
