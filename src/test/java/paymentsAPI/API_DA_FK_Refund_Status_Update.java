// Framework - Cleartrip Automation
// Author - Kiran Kumar

package test.java.paymentsAPI;

import java.io.IOException;
import java.util.Random;

import org.json.JSONException;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class API_DA_FK_Refund_Status_Update extends API_PaymentCommon1
{
	@Test
	public void paymentDA_FK_RefundCreate() throws Exception{
		
		String date = getDateTime(10, "dd/MM/yyyy");
		System.out.println(date);
		/*
		Response resp ;		
		resp = rearchPayment("DAFKRefundStatusUpdate","");	
		validation("DAFKRefundStatusUpdate", resp);*/
		}
}