package testScriptsMobile;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileCom_SnapDeal_Air_Dom_RT_Booking extends Mobile{
	public RemoteWebDriver driver;
	private String baseUrl;

@Test ( dataProviderClass = MobileDataProvider.class,dataProvider="MobileCom_Dom_RT")
public void mobileCom_SD_RT_Dom_412(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String Carrier1, String Carrier2, String Carrier3) throws Exception {
	  driver.get(baseUrl);
	  mobileCom_SD_Air_HomepageSearch_RoundTrip(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	  mobileCom_SD_Air_SRP_RoundTrip(driver, Carrier1, Carrier3, Carrier3);
	  mobileCom_SD_Air_ReviewItineraryPage(driver, "");
	  mobileCom_Air_TravelerPage(driver);
	  mobileCom_Air_MakePaymentPage(driver, "" ,"" , Trip_Logger_Msg);  
}

@BeforeClass
public void setUp() throws Exception {
	driver=getMobileDriver(driver);
	baseUrl = common.value("msdurl");
}

@AfterMethod(alwaysRun = true)
public void afterMethod(ITestResult _result) throws Exception {
	afterMethod(driver, _result);
}

@AfterClass
public void tearDown() throws Exception {
	  driver.quit();    
}

}