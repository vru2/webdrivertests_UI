// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsPayments;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commonServices.WrapperMethod;

	public class Payment_Dashboard extends WrapperMethod{
	public RemoteWebDriver driver;
	private String baseUrl;
	
		@Test
		public void Dashboard_Request() throws Exception {
		   driver.manage().deleteAllCookies();
		   driver.get(baseUrl);
			textPresent(driver, "PAYMENT STATS", 20);
			safeClick(driver, getObjectPayment("PaymentDashboard_HomePage_CC_LastDay"));
			textPresent(driver, "PAYMENT LOGS", 20);
		   safeClick(driver, getObjectPayment("PaymentDashboard_Logs_TripID"));
		   textPresent(driver, "PAYMENT DETAILS", 20);
		   safeClick(driver, getObjectPayment("PaymentDashboard_Details_Request"));
		   textPresent(driver, "Blob Details", 20);
		   String RequestHeaderText = getText(driver, getObjectPayment("PaymentDashboard_Details_Request_Page"));
		   
		   if(!RequestHeaderText.contains("PAYMENT_LOG_REQUEST")) {
			   Assert.assertTrue(false);
		   }
		   elementPresent(driver, getObjectPayment("PaymentDashboard_Details_Request_Page_XML"));
	  	}
		
		@Test
		public void Dashboard_Response() throws Exception {
		   driver.manage().deleteAllCookies();
		   driver.get(baseUrl);
			textPresent(driver, "PAYMENT STATS", 20);
			safeClick(driver, getObjectPayment("PaymentDashboard_HomePage_CC_LastDay"));
			textPresent(driver, "PAYMENT LOGS", 20);
		   safeClick(driver, getObjectPayment("PaymentDashboard_Logs_TripID"));
		   textPresent(driver, "PAYMENT DETAILS", 20);
		   safeClick(driver, getObjectPayment("PaymentDashboard_Details_Response"));
		   textPresent(driver, "Blob Details", 20);
		   String RequestHeaderText = getText(driver, getObjectPayment("PaymentDashboard_Details_Response_Page"));
		   
		   if(!RequestHeaderText.contains("PAYMENT_LOG_RESPONSE")) {
			   Assert.assertTrue(false);
		   }
		   elementPresent(driver, getObjectPayment("PaymentDashboard_Details_Response_Page_XML"));
	  	}
		
		@Test
		public void Dashboard_TripID() throws Exception {
			   driver.manage().deleteAllCookies();
			   driver.get(baseUrl);
				textPresent(driver, "PAYMENT STATS", 20);
				safeClick(driver, getObjectPayment("PaymentDashboard_HomePage_CC_LastDay"));
				textPresent(driver, "PAYMENT LOGS", 20);
			   safeClick(driver, getObjectPayment("PaymentDashboard_Details_Other_Info"));
			   textPresent(driver, "Blob Details", 20);
			   String RequestHeaderText = getText(driver, getObjectPayment("PaymentDashboard_Details_Other_Info_Text"));
			   
			   if(!RequestHeaderText.contains("PAYMENT_LOG_OTHER_INFO")) {
				   Assert.assertTrue(false);
			   }
			   elementPresent(driver, getObjectPayment("PaymentDashboard_Details_Request_Page_XML"));
		  	}
		

		@Test
		public void Dashboard_OtherDetails() throws Exception {
		   driver.manage().deleteAllCookies();
		   driver.get(baseUrl);
			textPresent(driver, "PAYMENT STATS", 20);
			
			safeType(driver, getObjectPayment("PaymentDashboard_TripID_TextBox"), "Q1810090623");
		   safeClick(driver, getObjectPayment("PaymentDashboard_TripID_Search_Btn"));
		   if(!textPresent(driver, "PAYMENT DETAILS", 20)) {
			   Assert.assertTrue(false);
		   }
		   
	  	}

	  @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		//baseUrl = "http://172.17.15.24/paymentDashboard";
		//http://172.17.13.131/paymentDashboard
		baseUrl = "http://172.17.14.217:9080/ct-logger/paymentDashboard";
	  }
  
  	@AfterMethod (alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  	@AfterClass
  	public void tearDown() throws Exception {
  		browserClose(driver);
  	}
}