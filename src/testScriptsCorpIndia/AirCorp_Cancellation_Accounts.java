// Framework - Cleartrip Automation
// Version -Web Driver 2.0
// Creation Date - Nov, 2016
// Author - Kiran Kumar
// Copyright © 2016 Cleartrip Travel. All rights reserved.

package testScriptsCorpIndia;

import static org.testng.AssertJUnit.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import domainServices.Corporate;

public class AirCorp_Cancellation_Accounts extends Corporate {
	public RemoteWebDriver driver;
	boolean flightCountFailure = true;
	String domain = "com";
	String TripID = null;

	@DataProvider
	public static Object[][] AirCorp() {
		return new Object[][] { { "MAA", "BLR", "19", "20", "1", "0", "0", "SG","","CC" } };
	}

	@Test(dataProvider = "AirCorp")
	public void CancelAcct_241(String FromCity, String ToCity, String From_Date, String To_Date, String Adults, String Childrens,	String Infants, String Pref_Airline, String flight_type,String PaymentType) throws Exception {
		driver.manage().deleteAllCookies();
		String SRP_URL = corpCom_Air_SrpUrl(driver, FromCity, ToCity, Adults, Childrens, Infants, "DomOW", Pref_Airline , 50, 51);
		driver.get(SRP_URL);		
		corpAir_SRP(driver, "DOMOW", flight_type);
		corpAir_ItineraryPage(driver);
		corpAir_TravellerPage(driver, Adults, Childrens, Infants,"false","false");
		if(MakePaymentOnlyInQA2){
		TripID = AirCorp_Paymentpage(driver, PaymentType, "", "Corp Acct Cancellation : ");
			safeClick(driver, By.linkText("Trips"));
		  	elementPresent_Time(driver, By.xpath("//h1"), 30);				
			elementPresent_Time(driver,getObject("AirCorp_TripsPage_SearchTrips"), 5);
			safeType(driver, getObject("AirCorp_TripsPage_SearchTrips"), TripID);
			safeClick(driver, By.xpath("//form[@id='SearchByTripId']/input[2]"));
			Thread.sleep(3000);
			smartClick(driver, By.id("listView_a"));			
			smartClick(driver, By.xpath("//p/a"));
			for(int i=0; i<=2; i++) {
			if(elementPresent_Time(driver, By.linkText("Cancellations"), 5)) {
				break;
				
			} 
			else if(textPresent(driver, "Oops! Cleartrip’s system is behaving badly", 1)){
				Reporter.log("Oops! Cleartrip’s system is behaving badly :  message is displayed");
				Assert.assertTrue(false);
			} else refreshPage(driver);
			}
			
			if(textPresent(driver, "We will send you the e-ticket shortly", 1)) {
				Reporter.log("We will send you the e-ticket shortly : message is displayed");
				Assert.assertTrue(false);
			}			
				safeClick(driver, By.linkText("Cancellations"));
				elementPresent_Time(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"), 10);
				assertTrue("Trip Cancellation Page Has Not Displayed", elementPresent(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]")));
				safeClick(driver, By.xpath("//input[starts-with(@id, 'group_box_ce_')]"));
				safeClick(driver, By.xpath("//*[@id='cancel']"));
				textPresent(driver, "Trip tools", 30);
				assertTrue("Trip Not Cancelled OR Corp System May Be Acting Up", textPresent(driver, "Cancelled", 5));
			}		
	}
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = (RemoteWebDriver) getDriver(driver);		
		baseUrl = getBaseUrl(domain);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult _result) throws Exception {
		afterMethod(driver, _result);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browserClose(driver);
	}
}