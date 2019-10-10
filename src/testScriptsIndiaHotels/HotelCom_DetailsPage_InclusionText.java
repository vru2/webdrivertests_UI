package testScriptsIndiaHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.IndiaHotelDataProvider;
import domainServices.IndiaHotels;
public class HotelCom_DetailsPage_InclusionText extends IndiaHotels {
	public RemoteWebDriver driver;
	private String baseUrl;
		
	@Test (dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComBangalore")
	public void HotelCom_DetailsPage_Inclusion_547(String City, String State, String Country, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
			   String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
		 
	{
		   driver.manage().deleteAllCookies();
		   driver.get(detailsPage_URL_Link(driver,"com", dataFile.value("HotelID_DetailsPage"), 10));
		   hotelCom_Inclusions_Verification(driver);      		   		   	   
	}
	
	}
	 @BeforeClass
	  public void setUp() throws Exception {
		driver=(RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl( "com");
	  }

	  @AfterMethod (alwaysRun = true)
		public void afterMethod(ITestResult _result) throws Exception {
			afterMethod(driver, _result);
		}


}
