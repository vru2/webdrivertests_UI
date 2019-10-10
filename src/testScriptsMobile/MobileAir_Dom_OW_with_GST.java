package testScriptsMobile;

import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileAir_Dom_OW_with_GST extends Mobile{
String TripID;
	public RemoteWebDriver driver;
	private String baseUrl;
HashMap<String,String> hp=new HashMap<String,String>();
  @Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileWl_Dom_OnewaySpicejet", groups={ "Smoke Test"})
  public void mobileCom_OW_Dom_413(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1) throws Exception {
	 System.out.println(baseUrl);
driver.get(baseUrl);
	  mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	  mobileCom_Air_SRP_Oneway1(driver, Carrier1);
	  mobileCom_Air_ReviewItineraryPage(driver, ""); 
	  mobile_Air_TravelerPage_with_GST(driver,true,Carrier1);
	  if ((common.value("makePayment").equals("true"))) {
	  mobileCom_Air_MakePaymentPage(driver, "" ,"" , Trip_Logger_Msg); 
	   TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"));
		 checkDetails(driver,TripID.split(":")[1].trim(),"SpiceJet",false);
	 }
	
	 
	 
	 
	 
  }

  @BeforeClass
  public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("murl");
  }

 /* @AfterMethod(alwaysRun = true)
  public void afterMethod(ITestResult _result) throws Exception {
  	afterMethod(driver, _result);
  }
*/  /*@AfterClass
  public void tearDown() throws Exception {
	  driver.quit();    
  }*/


}
