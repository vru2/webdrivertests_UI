// Framework - Cleartrip Automation
// Author - Kiran Kumar

package testScriptsIndiaHotels;

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

	public class HotelCom_AmexTravel_Prod extends IndiaHotels{
	public RemoteWebDriver driver;
	private String baseUrl2;
	
  @Test ( dataProviderClass = IndiaHotelDataProvider.class,dataProvider="HotelComCoupon")
  public void HotelCom_AmexTravel_MC_Validation(String City, String CheckIn_Date, String CheckOut_Date, String Rooms, String Adult1, String Adult2, String Adult3, String Adult4, 
		  							String Child1, String Child2, String ChildAge1, String ChildAge2, String Hotel_Name, String Payment_Type, String Logger_Msg, String Booking_Confirmation_Message) throws Exception {
       driver.manage().deleteAllCookies(); 
	   driver.get(baseUrl2);	  
	   hotelCom_HomepageSearch(driver, "Bangalore", CheckIn_Date, CheckOut_Date, Rooms, Adult1, Adult2, Adult3, Adult4, Child1, Child2,  ChildAge1, ChildAge2);
	   hotelCom_SRP(driver, "Hotel Mahaveer","");
	   hotelCom_ItineraryPage(driver, "");
	   hotelCom_LoginPage(driver, "","");
	   hotelCom_TravelerPage(driver);
	  
	   logMessagePageNotLoaded(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 20, "Payment Step has not loaded :( :( :( :( :( :(");
		if(elementVisible(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 5)) {
			} else {
			Reporter.log("Hotel Book Step 4 / Payment Step is not displayed");
		}
		waitElement(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"), 10);
	   
		Reporter.log("Payment Type is : CC");
		waitElement(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"), 10);
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Tab"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_No"), dataFile.value("MasterCard_Number"));
		safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Month"), dataFile.value("MasterCard_Exp_Month"));
		safeSelect(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_Exp_Year"), dataFile.value("MasterCard_Exp_Year"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_HolderName"), dataFile.value("MasterCard_HolderName"));
		safeType(driver, getObjectHotels("HotelCom_BookStep4_CreditCard_CVV"), dataFile.value("MasterCard_CVV"));
		smartClick(driver, By.id("native_currency"));
		Thread.sleep(2000);
		
		safeClick(driver, getObjectHotels("HotelCom_BookStep4_MakePayment_Button"));	
		
		elementPresent_log(driver, By.id("paymentBlockError"), "Amex Card Error message", 20);
		elementPresent_log(driver, By.xpath("//div[@id='CCTab']/dl/dd/small[3]"), "Enter Valid Amex card ", 2);
		
		textAssert(driver, By.id("paymentBlockError"), "Sorry, but we can't continue until you fix everything that's marked in RED");
		textAssert(driver, By.xpath("//div[@id='CCTab']/dl/dd/small[3]"), "Please enter a valid Amex card.");
  	}


  @BeforeClass
  public void setUp() throws Exception {
	driver=(RemoteWebDriver) getDriver(driver);
	baseUrl2 = getAmexTravelUrl();
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