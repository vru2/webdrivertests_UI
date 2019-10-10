package testScriptsMobile;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.MobileDataProvider;
import domainServices.AirCommonMethod;
import domainServices.Mobile;

public class Mobile_Intl_OW_GST extends Mobile{

	
	public RemoteWebDriver driver;
	public String baseUrl;
	String TripID;
	
	@Test(dataProviderClass=MobileDataProvider.class,dataProvider="MobileCom_Intl_OW_GST",groups={ "Smoke Test"})
	
	public void MobileCom_Intl_Ow_GST(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens, String Infants, String Trip_Logger_Msg) throws Exception {
	    driver.get(baseUrl);	
	
	    mobileCom_Air_HomepageSearch_Oneway(driver, FromCity, ToCity, From_Date, To_Date, Adults, Childrens,Infants);
	    mobileCom_Air_Intl_SRP_Filter_GST(driver,"IndiGo");
	    mobileCom_Air_ReviewItineraryPage(driver, "");
	    mobileCom_Air_IntlTravelerPage_With_GST(driver,"IndiGo");
	    if ((common.value("makePayment").equals("true"))) {
	    mobileCom_Air_MakePaymentPage(driver , "" ,"" , Trip_Logger_Msg);
	    TripID = getText(driver, getObject("MobileCom_Air_ConfirmationPage_TripID"));
		  		 checkDetails(driver,TripID.split(":")[1].trim(),"IndiGo",false);
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
	  }*/
	  
	  /*@AfterClass
	  public void tearDown() throws Exception {
		  driver.quit();    
	  }*/
	

}
