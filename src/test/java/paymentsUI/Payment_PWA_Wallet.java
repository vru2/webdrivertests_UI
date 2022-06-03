package test.java.  paymentsUI;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.java.domains.PaymentNodeJS;
import io.restassured.response.Response;
import junit.framework.Assert;
public class Payment_PWA_Wallet extends PaymentNodeJS {
	Response resp;
	public RemoteWebDriver driver =null;
	String apiurl="http://172.17.26.11:8358/paymentservice/api/getPaymentURL";
	String s=RandomStringUtils.randomNumeric(5);
	String param="{\"train_booking\":{\"traveller_details\":[{\"seq_no\":1,\"type\":\"ADT\",\"title\":\"Ms\",\"first_name\":\"Test\",\"last_name\":\"Test\"}],\"itinerary_details\":[{\"from_station_code\":\"SBC\",\"to_station_code\":\"MYS\",\"from_station_name\":\"KSR BENGALURU\",\"to_station_name\":\"MYSURU JN\",\"train_name\":\"BSB MYS EXP\",\"train_number\":\"16230\",\"departure_time\":\"2019-11-12T00:15:00\",\"arrival_time\":\"2019-11-12T02:45:00\",\"booking_class\":\"AC 2 Tier(2A)\",\"updated_availability\":\"AVAILABLE-0119\",\"quota\":\"General\",\"seatStatus\":true}],\"pricing_details\":[{\"other_railway_charges\":119.4,\"agent_service_charge\":40,\"total\":775.4,\"insuranceCharge\":0,\"currency\":\"INR\",\"pax_pay_info\":[{\"base_fare\":616.0,\"pax_count\":1,\"pax_type\":\"ADULT\"}]}],\"transaction_fee_details\":{\"CC\":{\"DEFAULT\":1.8},\"DC\":{\"DEFAULT\":1.0},\"NB\":{\"DEFAULT\":1.35,\"1\":1.1,\"2\":1.2,\"23\":1.1,\"3\":1.1},\"KC\":{\"DEFAULT\":0.0},\"DA\":{\"DEFAULT\":0.0},\"TW\":{\"DEFAULT\":1.0},\"UP\":{\"DEFAULT\":1.0}}},\"itinerary_id\":\"f25db800de1e0137664316217d236675\",\"ttl\":3600,\"trip_id\":46120910,\"app_ref1\":\"Q200117"+s+"\",\"app_ref2\":75251434,\"customer_detail\":{\"ip_address\":\"119.82.106.202\",\"mobile\":1212121212,\"landline\":1212121212,\"email\":\"cltppayment@gmail.com\",\"first_name\":\"Cltp\",\"last_name\":\"payment\",\"username\":\"cltppayment@gmail.com\"},\"product_type\":\"TRAIN\",\"currency\":\"INR\",\"order_info1\":\"16230/SBC/MYS/2019111200:15:00\",\"order_info2\":\"Test Test\",\"source_type\":\"ACCOUNT\",\"high_risk\":false,\"country\":\"IN\",\"user_id\":\"64891349\",\"email_id\":\"cltppayment@gmail.com\",\"d_plus_x_in_hours\":273,\"app_return_info\":{\"url\":\"https://qa2.cleartrip.com/trains/itinerary/f25db800de1e0137664316217d236675/process_payment\",\"method\":\"POST\",\"book_internal\":true,\"book_internal_url\":\"http://trains-book-nget.cltp.com:9001/r3/trains/itinerary/f25db800de1e0137664316217d236675/book_internal\",\"params\":null},\"payment_category\":\"B\"}";
	String paymenturl;
	
	@BeforeClass
	public String Setup() throws IOException, InterruptedException {
		/*paymenturl=getPaymenturl(driver,param,apiurl);
		  this.driver = getPWA(driver); baseUrl = getBaseUrl("com");
		  Thread.sleep(3000);*/
		 
	    return paymenturl;
	}
	
	
	  @Test 
	  public void makePayment() throws Exception {
	  driver.get(baseUrl+paymenturl); 
	  Thread.sleep(8000);
	  if(!(textPresent_Log(driver,"from wallet",20)))
	  {
	   textPresent_Log(driver,"DEBIT/CREDIT CARDS",40);
	  }
	  else if(textPresent_Log(driver,"from wallet",20))
	  { 
		  Reporter.log("User is already signed in");
		  Assert.assertFalse(true);
	  }
	 
	   Reporter.log("Unsigned validation is passed"); 
	  // cookie(driver);
	   refreshPage(driver); 
	   Thread.sleep(8000);
	   textPresent_Log(driver,"Select payment option",60);
	   textPresent_Log(driver,"from wallet",10);
	   if(elementPresent_log(driver,By.xpath("//label"),"Wallet enabled",10))
		{ 
		    safeClick(driver,By.xpath("//label"));
		    textPresent_Log(driver,"DEBIT/CREDIT CARDS",20);
		    safeClick(driver,By.xpath("//label"));
		    textPresent_Log(driver,"from wallet",20);
		    safeClick(driver,By.xpath("//button/p")); 
		    textPresent(driver,"Hang On",20); 
		    textPresent_Log(driver,"Hmm, something's not right.",60);
		    textPresent_Log(driver,"We couldn't complete the payment. Try again in ~20 minutes.",10);
		 }else 
		  { 
			 System.out.println("Wallet amount not present");
		     Reporter.log("Wallet amount not present"); 
		     Assert.assertFalse(true);
		  }
}
	 
	
	  @AfterClass 
	  public void closeSelenium() throws Exception 
	  {
	   browserClose(driver); 
	  }
	  
	  @AfterMethod(alwaysRun = true) 
	  public void afterMethod(ITestResult _result) throws Exception 
	  { 
		  afterMethod(driver,_result);
	  }
	 
}
