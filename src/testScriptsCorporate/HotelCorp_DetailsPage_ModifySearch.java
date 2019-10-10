package testScriptsCorporate;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import domainServices.CorporateHotels;

public class HotelCorp_DetailsPage_ModifySearch extends CorporateHotels {
	public RemoteWebDriver driver;

	@Test
	public void CorpHotel_BackButton() throws Exception {
	  driver.manage().deleteAllCookies();
	  String DetailsUrl =corpHotel_DetailsPageUrl(driver, "1126596", 50);
	  driver.get(DetailsUrl);
	  String CheckIn_Date = getDate( "dd");
	   CheckIn_Date = CheckIn_Date.substring(1);
	   CheckIn_Date = "1"+CheckIn_Date;
	   int DateInt = Integer.parseInt(CheckIn_Date);
	   DateInt = DateInt+1;
	 String CheckOut_Date = Integer.toString(DateInt);
	   selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckIn_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 2, CheckIn_Date);
	   selectCalendarDate(driver, getObjectHotels("HotelCom_HomePage_CheckOut_Cal"), getObjectHotels("HotelCom_HomePage_CheckIn_NextMonth"), 0, CheckOut_Date);
	   safeSelect(driver, By.xpath("//fieldset[1]/select"), "More travellers...");
		hotelCom_Homepage_PaxEntry(driver, "2", "2", "2", "0", "0", "1", "1", "5", "6");
		String FromDate = driver.findElement(By.id("CheckInDate")).getAttribute("value"); 
		String TODate = driver.findElement(By.id("CheckOutDate")).getAttribute("value");
		Reporter.log("Searched Hotel for CheckIn Date : " + FromDate + " and CheckOut Date : " + TODate);
	   safeClick(driver, By.xpath("//*[@value='Modify search']"));
	   if(elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_Rate_Unavailable"), 5)) {
		   Reporter.log("Rooms Are unavailable for the selected dates");
			Assert.assertTrue(false);
		}
		elementVisible(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"), 5);
		safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_SelectRoom"));
		Thread.sleep(500);
		safeClick(driver, getObjectHotels("HotelCom_DetailsPageNew_Book_Button"));	
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
