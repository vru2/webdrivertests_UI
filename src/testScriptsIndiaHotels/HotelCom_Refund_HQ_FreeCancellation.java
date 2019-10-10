package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.IndiaHotels;

public class HotelCom_Refund_HQ_FreeCancellation extends IndiaHotels{
	public RemoteWebDriver driver;
	private String TripID = null;
	
	@Test
	public void HotelCom_Refund_Acct_FC() throws Exception {
		driver.manage().deleteAllCookies();  
		hotelCom_DetailsPage(driver, "com", dataFile.value("HotelID_FreeCancellation"), 30, ""); 
		hotelCom_ItineraryPage(driver, "");
		hotelCom_LoginPage(driver, "SignIN", "");
		hotelCom_TravelerPage(driver);
		TripID =  hotelCom_PaymentPage(driver, "", "", "Your booking is done"); 
		hotelCom_HQ_Cancellation(driver, TripID);
		hotelCom_Refund_Cases(driver, TripID, "FC");
	  }
	
	@BeforeClass
	  public void setUp() throws Exception {
		  driver=(RemoteWebDriver) getDriver(driver);
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
