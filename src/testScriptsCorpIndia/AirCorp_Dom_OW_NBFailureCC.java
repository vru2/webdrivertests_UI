package testScriptsCorpIndia;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;

public class AirCorp_Dom_OW_NBFailureCC extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "AirCorp")
	public void airCorpDomOWNBFailureCC(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {

		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 28, 29);
		driver.get(SRP_URL);
		corpAir_SRP(driver, "DOMOW", "");
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		AirCorp_Paymentpage(driver, "NBFailure", "" , "Corp Dom OW NB Failure");
		
		for(int i=0;i<=10;i++) {
			if(elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 1)) {
				break;
			} else if(elementVisible(driver, By.xpath("(//a[contains(text(),'Modify search')])[2]"), 1)) {
				Reporter.log("Page has redirected back to SRP after clicking on continue in Traveller page");
				Assert.assertTrue(false);
			}
		}
		elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 20);
		String TripID= null;
		if(!elementVisible(driver, getObject("AirCorp_PaymentPage_Coupon_TextBox"), 60))
		{	
			Reporter.log("Page has not redirected to Payment retry page");
			Assert.assertTrue(false);
		}/*
		
		if (MakePaymentTrue){
		if(corpAir_ConfirmReturnToPaymentPage(driver)) {
			AirCorp_Paymentpage(driver, "CC", "" , "Corp Dom OW NB Failure");
		}
		
		}*/
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		baseUrl = getBaseUrl(domain);
	}

	@AfterClass
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}
