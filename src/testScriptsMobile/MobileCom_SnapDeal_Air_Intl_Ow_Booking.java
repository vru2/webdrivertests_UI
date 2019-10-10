package testScriptsMobile;


import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileCom_SnapDeal_Air_Intl_Ow_Booking extends Mobile{
	
	public RemoteWebDriver driver;
	public String baseUrl;
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Intl_Ow")
	
	public void mobileCom_SD_Intl_Ow_411(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg) throws Exception {
	    
		driver.get(baseUrl);	
	
		mobileCom_SD_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
		mobileCom_SD_Air_IntlSRP_Oneway(driver);
	   mobileCom_Air_ReviewItineraryPage(driver, "");
	   
	    mobileCom_Air_IntlTravelerPage(driver);
	    mobileCom_Air_MakePaymentPage(driver , "" ,"" , Trip_Logger_Msg);

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

