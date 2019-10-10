// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Oct, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import dataServices.CorporateDataProvider;
import domainServices.Corporate;

public class AirCorp_Intl_OW_ConvenienceFee_NB extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";

	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);
		if (driver == null) {
			Reporter.log("Error in initial setup. Exiting without screenshot");
			throw new SkipException("Skipping Test: ");
		}
		baseUrl = getBaseUrl(domain);
	}
/*
	@DataProvider
	public static Object[][] AirCorpInt() {
		return new Object[][] { { "Delhi", "Dubai", "21", "22", "1", "0", "0", "","lcc" } };
	}
*/	
	@Test(dataProviderClass = CorporateDataProvider.class, dataProvider = "AirCorpInt")
	public void airCorpIntlOW(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,
			String Infants, String Pref_Airline) throws Exception {

		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "IntlOW", Pref_Airline , 45, 46);
		driver.get(SRP_URL);

		corpAir_ConfirmSRPLoadCompleteIntlOneway(driver);

		if(elementVisible(driver, By.id("1_1_S2"), 1)) {
			UnChecking_Checkbox(driver, By.id("1_1_S2"));
		}
		corpAir_SRP_Intl_Oneway(driver);
		if(!elementVisible(driver, getObject("Air_Agency_Itinerary_ContinueButton"), 30)){
			Reporter.log("Itinerary Page has not loaded");
		}
		elementVisible(driver, By.id("rtTotalAmount"), 20, "Price is not displayed in Itinerary");
		safeUncheck(driver,getObject("air_step1_insurance_chkbox"));
		int totalPrice_Int  = priceCapture(driver, By.id("rtTotalAmount"), 20);
		Reporter.log("Price in Itinerary page : "+totalPrice_Int);
		corpAir_ItineraryPage(driver);
		corpAir_Intl_TravellerPage(driver, Adults, Childrens, Infants);
		//safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));
		/*waitElement(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"), 10);
		safeClick(driver, getObject("AirCorp_PaymentPage_NetBanking_Tab"));

		safeSelect(driver, getObject("AirCorp_PaymentPage_NetBank_Dropdown"), "Bank of India");
		elementVisible(driver, By.id("processingFeeAmount"), 20, "Price is not displayed in PaymentPage");
		int price_Con_Int = priceCapture(driver, By.id("processingFeeAmount"), 20);
		int totalPrice_Con_Int = priceCapture(driver, By.id("formTotal"), 20);
		Reporter.log("PG Fee in Payment Step : "+price_Con_Int);
		Reporter.log("PG Fee + Price in Payment Step : "+totalPrice_Con_Int);
		//System.out.println("PG Fee in Payment Step : "+price_Con_Int);
		//System.out.println("PG Fee + Price in Payment Step : "+totalPrice_Con_Int);
		if(!(totalPrice_Int+price_Con_Int == totalPrice_Con_Int)) {
			Reporter.log("Price + PG fee is not matching in Payment Step");
			Assert.assertTrue(false);
		} else Reporter.log("Price + PG fee is matching in Payment Step");
		//AirCorp_Paymentpage(driver, "NBBOI", "", "Corp Intl OW : ");
		*/
		
		  if(!elementPresent(driver, getObject("AgencyHotels_PaymentPage_CreditCard_Tab"),90)){
				Reporter.log("PaymentPage is not displayed");
			}
			safeClick(driver, getObject("AgencyHotels_PaymentPage_NetBanking_Tab"));
			safeSelect(driver, getObject("AgencyHotels_PaymentPage_NetBank_Dropdown"), "Bank of India");	
				 int pricePayment = priceCapture(driver, By.id("formTotal"), 5);
				  int priceConFee = priceCapture(driver, By.id("processingFeeAmount"), 5);
				  Reporter.log("Price in traveller page : "+totalPrice_Int);
				  Reporter.log("Price in payment page : "+pricePayment);
				  Reporter.log("Convience fee : "+priceConFee);
				  if(!(totalPrice_Int+priceConFee == pricePayment)) {
					  Reporter.log("Price - Traveller page + ConvienenceFee & Payment page doesnt match");
					  Assert.assertTrue(false);
				  }
	}

	@AfterMethod(alwaysRun = true)
	public void takeScreenshot(ITestResult _result) throws Exception {
		screenshot(_result, driver);
		driver.manage().deleteAllCookies();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}
}
