// Fra[mework - Cleartrip Automation
// Author - Saloni Gothi

package paymentsAPI;

import java.util.List;
import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API_Refunds2 extends API_PaymentCommon1
{
	
	public void refund_entry(String tripid, String amount,Boolean isFullWalletRefund, String status) throws Exception{
		
		Random rand = new Random();
		int n = rand.nextInt(9999999);
		String txnid = Integer.toString(n);
		Response resp ;	
		
		
			resp = refunds("refunds_createrefund",txnid,tripid, amount, isFullWalletRefund, status );	
			validation("refunds_createrefund", resp);
								
			    JsonPath jsevaluator = resp.jsonPath();
		        List refundids = jsevaluator.getList("refundIds");
		        List successrefunds = jsevaluator.getList("successfulRefundedIds");
		        
			   // String refundid = jsevaluator.getString("refundIds").substring(1,8);
			  	  String refundid = refundids.get(0).toString();
		   
				 if(status!=null)
				 {
					resp = refunds("refunds_updatestatus",refundid,"","",false ,"");	
					validation("refunds_updatestatus", resp);
					
						
					resp = refunds("refunds_updatepgtxnid",refundid,"","",false,"");	
					validation("refunds_updatepgtxnid", resp);
					
//					resp = refunds("refunds_getrecord",refundid,"","",false,"");	
//					validation("refunds_getrecord", resp);
				 }					
				 		 
					 for (int i=0;i< refundids.size();i++)
					 {
						 
						String processrefundid = refundids.get(i).toString();	
						
						resp = refunds("refunds_getrecord",processrefundid,"","",false,"");	
						validation("refunds_getrecord", resp);
						
						String refundstatus = resp.jsonPath().getMap("refund").get("status").toString();
						Reporter.log(processrefundid +" refund status is : " + refundstatus);
						
						if(refundstatus!= "D")
						{	
						resp = refund("refund", processrefundid);
						validation("", resp);
						Reporter.log("Updated status " +resp.body().asString());
						}
												
					 
				 }
		
	}

	/*@Test(alwaysRun=true)
	public void refund_walletCC() throws Exception{
		
		refund_entry("Q190814464372", "20.75", true, "\"S\"");//FullCC- 39


	}
		@Test(alwaysRun=true)
	public void refund_walletWT() throws Exception{
		
		refund_entry("Q190801451410", "209.75" , true, "\"S\"" );//FullWT-2079
		
	}*/
	@Test(alwaysRun=true)
	public void refund_walletMultiGV() throws Exception{
		
		refund_entry("Q190808459998", "2650.87" , true, "\"S\"" ); //MultiGV -971+50+1661
		
	}
	/*@Test(alwaysRun=true)
	public void refund_walletRP() throws Exception{
		
			refund_entry("Q190806453914", "20.45" , true, "\"S\""); //FullRP - 121
		
	}*/
	/*
	@Test(alwaysRun=true)
	public void refund_Originalmultipay_CCWTGV() throws Exception{
		
			refund_entry("Q190806453624", "1650.75", false, null);//CC+WT+GV-1100+50+518

	}
	@Test(alwaysRun=true)
	public void refund_Originalmulti_WTGVRP() throws Exception{
		
		refund_entry("Q190806453956", "113.75", false, null);//WT+GV+RP-50+10+61

	}*/
		
	
	
}