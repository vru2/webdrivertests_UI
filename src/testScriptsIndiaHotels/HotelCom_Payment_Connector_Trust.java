package testScriptsIndiaHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Payment_Connector_Trust extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	@Test
	public void HotelCom_Booking_Trust() throws Exception {
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);
	       //*hotelCom_HomepageSignIn(driver, "");
	       SRP_URL_Debug(driver, "com", "bangalore", "karnataka", "IN","", 100);
	       hotelCom_SRP(driver, "","Supp_Trust");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   TripID =  hotelCom_PaymentPage(driver, "CREDITCARD", "Trust Booking : ", "");
		   if(MakePaymentOnlyInQA2){
			     hotelCom_Account_Cancellation(driver, TripID,"");
			     driver.get(baseUrl+"/account/trips/"+TripID);
			     if(!elementVisible(driver, By.cssSelector("span.status.negative"), 20)){
				   Reporter.log("Trust booking is not Cancelled : "+getText(driver, By.xpath("//td[4]/span")));
				   Assert.assertTrue(false);
			     } else Reporter.log("Trust is Cancelled");
			     if(!getText(driver, By.xpath("//td[3]")).contains("TEST")){
				   Reporter.log("Trust is not having test credentials : "+getText(driver, By.xpath("//td[3]")));
				   Assert.assertTrue(false);
			     } 
			     else Reporter.log("Trust is having test credentials : "+getText(driver, By.xpath("//td[3]")));
			     hotelCom_Open_TripID_HQ(driver, TripID);
			     elementVisible(driver, By.xpath("//tr[3]/td[2]"), 10);
			     if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("Trust")){
			    	 Reporter.log("Its not a trust booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
			    	 Assert.assertTrue(false);		
			}			    
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
