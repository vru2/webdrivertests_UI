// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsCorporate;

import java.util.ArrayList;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.CorporateHotels;

	public class HotelCorp_DetailsPage_CancellationPolicy extends CorporateHotels{
	public RemoteWebDriver driver;
	public String HotelID = "";
	
	@Test
	public void CorpHotel_Cancel_Free() throws Exception {
	  if(ProductionUrl) {
	    	 HotelID = dataFile.value("HotelID_B2B_Cancellation_Free_Prod");
	     } else HotelID= dataFile.value("HotelID_B2B_Cancellation_Free");
	  String DetailsUrl =corpHotel_DetailsPageUrl_NoSignIN(driver, HotelID, 50);
	  driver.get(DetailsUrl);
	  detailsPage_CancellationPolicy(driver, "Refundable");
	  itineraryPage_BookingPolicy("If you cancel within 72 hours before checkin, you will incur 100.0% of your total stay.");	  
	}

	@Test (dependsOnMethods={"CorpHotel_Cancel_Free"}, alwaysRun=true)
	public void CorpHotel_Cancel_NonRefundable() throws Exception {
	  if(ProductionUrl) {
	    	 HotelID = dataFile.value("HotelID_B2B_Cancellation_NonRefundable_Prod");
	     } else HotelID= dataFile.value("HotelID_B2B_Cancellation_NonRefundable");
	  String DetailsUrl =corpHotel_DetailsPageUrl_NoSignIN(driver, HotelID, 50);
	  driver.get(DetailsUrl);
	  detailsPage_CancellationPolicy(driver, "Nonrefundable");
	  itineraryPage_BookingPolicy("This booking is non-refundable.");
	}
	
	public void itineraryPage_BookingPolicy(String CancellationText) throws InterruptedException, Exception {
		for (int i = 0; i < 5; i++) {
			if (elementVisible(driver, getObjectHotels("HotelCorp_ReviewPage_Book_Button"), 5)) {
				break;
			} else if (textPresent(driver, "Hotel no longer available", 1)) {
				Reporter.log("Hotel no longer available : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
			} else if (textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)) {
				Reporter.log("Oops! Cleartrip’s system is behaving badly : Message is displayed in Itinerary page");
				Assert.assertTrue(false);
			}
			if (!elementVisible(driver, getObjectHotels("HotelCorp_ReviewPage_Book_Button"), 5)) {
				Reporter.log("Itinerary page is not displayed");
				Assert.assertTrue(false);
			}
	  }
	  safeClick(driver, getObjectHotels("HotelCorp_ReviewPage_BookingPolicy"));
	  Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		textPresent(driver, "Cleartrip hotel booking policy",10);
			/*if(!textPresent(driver, CancellationText, 10)) {
				Reporter.log(CancellationText+" : text is not displayed");
				Assert.assertTrue(false);			
		}*/
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0));
	}
	
   @BeforeClass
    public void setUp() throws Exception {
	  driver=(RemoteWebDriver) getDriver(driver);
	  String DetailsUrl =corpHotel_DetailsPageUrl(driver, "1126596", 50);
	  driver.get(DetailsUrl);	  
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