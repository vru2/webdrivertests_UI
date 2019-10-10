// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.IndiaHotels;

	public class HotelCom_DetailsPage_PaxChange_Cancl_Acct extends IndiaHotels{
	public RemoteWebDriver driver;
	private String TripID = null;
	
	 @Test 
	  public void HotelCom_Details_PaxChange() throws Exception {
			 	hotelCom_DetailsPage_URL_Open1(driver, "com", "717060", 10);
			 	elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 20);
	 			Thread.sleep(2000);
			 	PAX_Rate("1 Room, 1 Adult", "100");
			 	PAX_Rate("1 Room, 2 Adults", "200");
			 	PAX_Rate("1 Room, 3 Adults", "300");
			 	PAX_Rate("2 Rooms, 4 Adults", "400");			
	 }
	 
	 @Test(dependsOnMethods= {"HotelCom_Details_PaxChange"})
	  public void HotelCom_Cancel_Acct() throws Exception {
			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
			Thread.sleep(500);
			safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));			
		 	hotelCom_AddCookie(driver);
		    hotelCom_ItineraryPage(driver, "");
		 	hotelCom_TravelerPage(driver);
			TripID =  hotelCom_PaymentPage(driver, "CREDITCARD", "Cancel Accts CCValidation ", ""); 
			hotelCom_Account_Cancellation(driver, TripID,"CCVALIDATION");
	 }
	  
	  	public void PAX_Rate(String PAX, String PaxPrice) throws Exception {
	  		String Price;	
			List<WebElement> we = driver.findElements(By.xpath("//div[5]/ul/li"));
		 	for (WebElement pax : we) {
				if(pax.getText().contains(PAX)) {
		 			pax.click();
		 			Thread.sleep(5000);
		 			elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate"), 5);
		 			Price = getText(driver, getObjectHotels("HotelCom_DetailsPageNew_Min_Rate"));
		 			Price = Price.replaceAll("[^\\d]", "");
		 			if(!Price.equals(PaxPrice))
		 			{	
		 				Reporter.log("Price : "+Price);
		 				Assert.assertTrue(false);
		 			}
		 		}
		 	}
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