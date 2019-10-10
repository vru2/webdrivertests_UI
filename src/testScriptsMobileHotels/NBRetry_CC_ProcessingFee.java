// Framework - Cleartrip Automation
// Author - Kiran Kumar 

package testScriptsMobileHotels;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.MobileHotels;

	public class NBRetry_CC_ProcessingFee extends MobileHotels{
	public RemoteWebDriver ConvenienceFee;
	private String baseUrl;

  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Hotel_SinglePax")
  public void MobileCom_Hotel_SinglePax_SignedIn_NBRetry_SC_430(String City, String From_Date, String To_Date, String Adults, String Childrens, String Hotel_Name, String Trip_Logger_Msg) throws Exception {
	  driver.manage().deleteAllCookies();  
	  driver.get(mobileCom_Hotel_SRP_URL(driver, "Com", "Bangalore", "Karnataka"));	 
	  //	 mobileCom_Hotel_HomepageSearch(driver, City, From_Date, To_Date, Adults, Childrens);
	  mobileCom_Hotel_SRP(driver, "");
	  hotelCom_AddCookie(driver);
	  mobileCom_Hotel_Details(driver);
	  mobileCom_Hotel_RoomType(driver);
	  mobileCom_Hotel_ItineraryPage(driver, "");
	  mobileCom_Hotel_TravellerPage(driver);
	  elementPresent_log(driver, getObject("MobileCom_Hotel_PaymentPage_ProcessingFee"), "Convenience fee in Payment page", 5);
	  String ConvenienceFee = getText(driver, getObject("MobileCom_Hotel_PaymentPage_ProcessingFee"));
	  mobileCom_Hotel_PaymentPage(driver, "NBRetry", "NB Retry");	  
	  String ConvenienceFeeRetry = getText(driver, getObject("MobileCom_Hotel_PaymentPage_ProcessingFee"));
	  if(!ConvenienceFee.equals(ConvenienceFeeRetry)){
		  Reporter.log("Convenience fee in first attempt : "+ConvenienceFee+" Convenience fee after NB retry "+ConvenienceFeeRetry);
		  Assert.assertTrue(false);
	  }
	  mobileCom_Hotel_PaymentPage(driver, "", "NB Retry with CC");	  
	  mobileCom_Hotel_ConfirmationPage(driver, "", "B2C NB retry ProcessingFee check ", "Your booking is done");
	  String ConfirmationPageConvenienceFee = null;
	  for(int i=1; i<=3; i++){
		  String PricingText = getText(driver, By.xpath("//dt["+i+"]"));
		  if(PricingText.contains("Convenience fee")){
			  ConfirmationPageConvenienceFee= getText(driver, By.xpath("//dd["+i+"]"));
			  break;
		  }
	  }
	  ConvenienceFee = ConvenienceFee.replace("Rs.", "");
	  if(!ConfirmationPageConvenienceFee.contains(ConvenienceFee)){
		  Reporter.log("ConfirmationPage ConvenienceFee is "+ConfirmationPageConvenienceFee+"PaymentPage ConvenienceFee"+ConvenienceFee);
		  Assert.assertTrue(false);
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