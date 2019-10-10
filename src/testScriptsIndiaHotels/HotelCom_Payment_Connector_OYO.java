// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_OYO extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	@Test 
	public void HotelCom_Booking_OYO() throws Exception {
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);
	       SRP_URL_Debug(driver, "com", "Bangalore", "", "IN","", 5);
	       hotelCom_SRP(driver, "","Supp_OYO");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   TripID =  hotelCom_PaymentPage(driver, "CREDITCARD", "OYO Booking : ", "");
		   if(MakePaymentOnlyInQA2){
			   driver.get(baseUrl+"/account/trips/"+TripID);
			   elementVisible(driver, By.xpath("//td[3]"), 20);
			   if(!getText(driver, By.xpath("//td[3]")).contains("")){
				   Reporter.log("OYO is not having test credentials : "+getText(driver, By.xpath("//td[3]")));
				   Assert.assertTrue(false);
			   }
			   hotelCom_Account_Cancellation(driver, TripID,"");
			   driver.get(baseUrl+"/account/trips/"+TripID);
			   if(!elementVisible(driver, By.cssSelector("span.status.negative"), 20)){				   
				   Reporter.log("OYO booking is not Cancelled : "+getText(driver, By.xpath("//td[4]/span")));
				   Assert.assertTrue(false);
			   }
			   Reporter.log("OYO  credentials : "+getText(driver, By.xpath("//td[3]")));
			   hotelCom_Open_TripID_HQ(driver, TripID);
			   elementVisible(driver, By.xpath("//tr[3]/td[2]"), 10);
			   if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("OYORooms")){
				   Reporter.log("Its not a OYO booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
				   Assert.assertTrue(false);		
			   }else  Reporter.log("Its a OYO booking ");
			}
	}
	 
	@BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }
	
	 @AfterMethod (alwaysRun = true)
	 public void takeScreenshot(ITestResult _result) throws Exception{
		 screenshot(_result, driver);
	 }

	  @AfterClass
	  public void tearDown() throws Exception {
		  browserClose(driver);
	  }

}
