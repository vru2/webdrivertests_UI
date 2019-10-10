package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.Mobile;

public class MobileCom_Dom_Cancellation extends Mobile {
	
	public RemoteWebDriver driver;
	public String baseUrl; String TripID= null;
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Dom_OW")
	
	public void mobileCom_Dom_Cancellation_419(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg, String GoAir, String SpiceJet, String Indigo) throws Exception {
    driver.manage().deleteAllCookies();
	driver.get(baseUrl);
	 mobileCom_Air_HomepageSearch_Oneway(driver, "DELHI", "BOMBAY", From_Date, To_Date, Adults, Childrens,Infants);
    mobileCom_Air_SRP_Oneway(driver, Indigo, SpiceJet, GoAir);
    mobileCom_Air_ReviewItineraryPage(driver, "");
	mobileCom_Air_TravelerPage(driver);
	if ((common.value("makePayment").equals("true"))) {
	String TripID = mobileCom_Air_MakePaymentPage(driver, "" ,"" , " Air Cancellation ");  
	TripID = TripID.replace("Trip ID: ", "");
	mobileCom_Air_CancellationFlow(driver,TripID);  
	}
  }
	 @BeforeClass
	  public void setUp() throws Exception {
		driver=getMobileDriver(driver);
		baseUrl = common.value("murl");
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
