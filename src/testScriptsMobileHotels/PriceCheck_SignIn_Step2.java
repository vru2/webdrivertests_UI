// Framework - Cleartrip Automation
// Author - Gowtham Sadhashivam

package testScriptsMobileHotels;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class PriceCheck_SignIn_Step2 extends MobileHotels{
	public RemoteWebDriver driver;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void MobileCom_Hotel_PriceCheck_428(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();  
	  String SRP_URL = mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka");
	  driver.get(SRP_URL);	 
	  mobileCom_Hotel_SRP(driver, "");
	  mobileCom_Hotel_Details(driver);	  
	  int Price_Int = mobileCom_Hotel_Detailspage_PriceValidation(driver);	  
	  mobileCom_Hotel_RoomType(driver);	  
	  mobileCom_Hotel_ItineraryPage_PriceValidation(driver, Price_Int);	  
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_Login(driver, "SIGNIN");
	  mobileCom_Hotel_TravellerPage(driver);
	  mobileCom_Hotel_PaymentPage(driver, "", " Hotel Price Check ");	  
	  if(MakePaymentOnlyInQA2){
	  mobileCom_Hotel_Confirmationpage_PriceValidation(driver, Price_Int);
	  }
  }


  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver1(driver);
	baseUrl = common.value("murl");
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