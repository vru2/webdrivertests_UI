// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import domainServices.MobileHotels;

	public class Meta_Trivago extends MobileHotels{
	public RemoteWebDriver driver;
	String baseUrl;

  @Test
  public void mobileCom_MobileWeb_GoogleHPA() throws Exception {
	  String HotelName = dataFile.value("HotelName_Mobileweb_UTM_Trivago");
	  String CheckIn_Date = putDate1(20);
	  String CheckOut_Date = putDate1(21);
	  String CityName = "Bangalore";
	  String StateName = "Karnataka";
	  driver.manage().deleteAllCookies();  
	  driver.get(baseUrl+"/m/hotels/results?city="+CityName+"&country=IN&state="+StateName+"&ckIn="+CheckIn_Date+"&ckOut="+CheckOut_Date+"&noOfRooms=1&ad=2&cd=0&pahCCRequired=true&utm_source=Trivago&#filter?filterType=hotelName&filterValue="+HotelName);
	  elementVisible(driver, getObject("MobileCom_Hotel_SRP_Price_Sort"), 10);
	  safeClick(driver, getObject("MobileCom_Hotel_SRP_Hotel")); 
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver,"Meta");
	  mobileCom_Hotel_Login(driver, "SignIn");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "", "Mobile Web Trivago");
	  mobileCom_Hotel_ConfirmationPage(driver, "", "Mobile Web Trivago", "Your booking is done"); 
  }

  @BeforeClass
  public void setUp() throws Exception {
	  driver=getMobileDriver1(driver);
	  baseUrl = getBaseUrl( "com");
  }

  @AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}
  
  @AfterClass
  public void tearDown() throws Exception {
	browserClose(driver);
  }

}