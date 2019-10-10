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

public class HotelCom_Payment_Connector_GTA extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
	private String TripID = null;
	
	@Test
	public void HotelCom_Booking_GTA() throws Exception {
	{
		   driver.manage().deleteAllCookies();
	       driver.get(baseUrl);
	       hotelCom_AddCookie(driver);  
	       SRP_URL_Debug(driver, "com", "Singapore", "", "SG","", 100);
	       hotelCom_SRP_Intl_Connector(driver, "","Supp_GTA");
		   hotelCom_ItineraryPage(driver, "");
		   hotelCom_TravelerPage(driver);
		   TripID =  hotelCom_PaymentPage(driver, "CREDITCARD", "GTA Booking : ", "");
		   if(MakePaymentOnlyInQA2){			
		   hotelCom_Account_Cancellation(driver, TripID,"");
		   driver.get(baseUrl+"/account/trips/"+TripID);
		   if(!elementVisible(driver, By.cssSelector("span.status.negative"), 20)){
			   Reporter.log("GTA Cancelation Done : "+getText(driver, By.xpath("//td[4]/span")));
			   Assert.assertTrue(false);
		   }		   
		   else Reporter.log("GTA Cancelation is Done : "+getText(driver, By.xpath("//td[3]")));	
		   Reporter.log("GTA  credentials : "+getText(driver, By.xpath("//td[3]")));		   
		   hotelCom_Open_TripID_HQ(driver, TripID);
		   elementVisible(driver, By.xpath("//tr[3]/td[2]"), 10);
		   if(!getText(driver, By.xpath("//tr[3]/td[2]")).contains("GTA")){
			   Reporter.log("Its not a GTA booking : "+getText(driver, By.xpath("//tr[3]/td[2]")));
			   Assert.assertTrue(false);		
		   } else Reporter.log("Its a GTA booking ");
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
